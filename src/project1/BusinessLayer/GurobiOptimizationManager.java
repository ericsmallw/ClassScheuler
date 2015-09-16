/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.BusinessLayer;

import gurobi.GRB;
import gurobi.GRBEnv;
import gurobi.GRBException;
import gurobi.GRBLinExpr;
import gurobi.GRBModel;
import gurobi.GRBVar;
import java.util.ArrayList;
import project1.Course;
import project1.Semester;
import project1.Student;


public class GurobiOptimizationManager implements IOptimizationManager {
    @Override
    public String minimizeClassSize(Course[] courses, Student[] students, 
                                    int numberOfSemesters, int allowedNoOfCoursesPerSemester) {
        String courseSize = "";
        try {
            //initialize model
            GRBEnv env = new GRBEnv("");
            //env.set(GRB.IntParam.OutputFlag, 0);
            GRBModel model = new GRBModel(env);
            
            //create variables
            GRBVar[][][] y = new GRBVar[students.length][courses.length][numberOfSemesters];
            for(int i = 0; i < students.length; i++){
                for(int j = 0; j < courses.length; j++){
                    for(int k = 0; k < numberOfSemesters; k++){
                        String varName = "Y" + String.valueOf(i)+ String.valueOf(j) + String.valueOf(k);
                        y[i][j][k] = model.addVar(0, 1, 0.0, GRB.BINARY, varName);
                    }
                }
            }
            
            GRBVar x = model.addVar(0, GRB.INFINITY, 1, GRB.INTEGER, "X");
            
            model.update();
            
            //set up objective function
            GRBLinExpr expr = new GRBLinExpr();
            expr.addTerm(1, x);
            model.setObjective(expr, GRB.MINIMIZE);
            
            //constraints
            
            //each course should be taken once unless the student isnt taking the class
            for(int i = 0; i < students.length; i++){
                for(int j = 0; j < courses.length; j++){
                    GRBLinExpr courseOnlyOnce = new GRBLinExpr();

                    for(int k = 0; k < numberOfSemesters; k++){
                        GRBVar var= y[i][j][k];
                        courseOnlyOnce.addTerm(1, var);
                    }   
                    int rhs = students[i].getScheduledCourses().contains(j) ? 1 : 0;
                    model.addConstr(courseOnlyOnce, GRB.EQUAL, rhs, null);
                }
            }
            
            //students can only take a certain number of courses each semester
            for(int i = 0; i < students.length; i++){
                for(int k = 0; k < numberOfSemesters; k++){
                    ArrayList<GRBVar> terms = new ArrayList<>();
                    GRBLinExpr semsesterTwoClasses = new GRBLinExpr();
                    for(int j = 0; j < courses.length; j++){
                        GRBVar var= y[i][j][k];
                        semsesterTwoClasses.addTerm(1, var);
                    }
                    
                    model.addConstr(semsesterTwoClasses, GRB.LESS_EQUAL, allowedNoOfCoursesPerSemester, null);
                }
            }
            
            //prerequisites
            for(int i = 0; i < students.length; i++){
                Student student = students[i];
                for(int j = 0; j < student.getScheduledCourses().size(); j++){
                    switch(j){
                        case 0:
                        case 6:
                        case 12:
                        case 15:
                            int[] pr = courses[j].getPrereqIds();
                            int prCourse = 0;
                            GRBLinExpr lhs = new GRBLinExpr();
                            GRBLinExpr rhs = new GRBLinExpr();
                            for(int l = 0; l < pr.length; l++){
                                for(int k = 0; k < numberOfSemesters; k++){
                                    lhs.addTerm(k+1, y[i][j][k]);
                                    rhs.addTerm(k+1, y[i][pr[l]][k]);
                                }
                            }

                            model.addConstr(lhs, GRB.LESS_EQUAL, rhs, null);
                            
                            break;
                        default:
                            break;
                    }
                            
                }
            }
            
            //capacity limits   
            for(int j = 0; j < courses.length; j++){
                for(int k = 0; k < numberOfSemesters; k++){
                    GRBLinExpr capacityLimit = new GRBLinExpr();
                    
                    for(int i = 0; i < students.length; i++){
                        GRBVar var= y[i][j][k];
                        capacityLimit.addTerm(1, var);
                    }
                    
                    GRBLinExpr rhs = new GRBLinExpr();
                    
                    int rightHandCoefficient = 0;
                    
                    //check to see if the current semester matches the one of the
                    //available semesters of the course if so the rhs coefficient
                    //should be one
                    for(Semester semester: courses[j].getSemestersAvailable()){
                        if((k + 1) % 3 == (semester.ordinal() + 1)){
                            rightHandCoefficient = 1;
                            break;
                        }
                    } 
                    
                    rhs.addTerm(rightHandCoefficient, x);
                    model.addConstr(capacityLimit, GRB.LESS_EQUAL, rhs, null);
                }
            }
            
            model.optimize();
            courseSize = String.valueOf((int)x.get(GRB.DoubleAttr.X));
        } catch (GRBException ex) {
            ex.printStackTrace();
        }
        
        return courseSize;
    }
    
}
