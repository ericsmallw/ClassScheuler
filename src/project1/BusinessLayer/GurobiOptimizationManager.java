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
import java.util.logging.Level;
import java.util.logging.Logger;
import project1.Course;
import project1.Semester;
import project1.Student;


public class GurobiOptimizationManager implements IOptimizationManager {
    
     @Override
    public String getMinimizedCourseSize(ArrayList<Course> courses, ArrayList<Student> students, 
                                    int numberOfSemesters, 
                                    int allowedNumberOfCoursesPerSemester) {
        
        
        
         try {
             GRBModel model = minimizeCourseSize(courses, students, numberOfSemesters, allowedNumberOfCoursesPerSemester);
             GRBVar x = model.getVarByName("X");
             return String.valueOf((int)x.get(GRB.DoubleAttr.X));
         } catch (GRBException ex) {
             Logger.getLogger(GurobiOptimizationManager.class.getName()).log(Level.SEVERE, null, ex);
         }
         return "Operation Out of Bounds";
    }
    
     @Override
    public String getCourseScheduleForStudent(ArrayList<Course> courses, ArrayList<Student> students, 
                                    int numberOfSemesters, 
                                    int allowedNumberOfCoursesPerSemester, int studentNumber) {
        try {
             GRBModel model = minimizeCourseSize(courses, students, numberOfSemesters, allowedNumberOfCoursesPerSemester);
             String schedule = "";
             for(int k = 0; k < numberOfSemesters; k++){
                 schedule += "Semester " + k + ": ";
                for(int j = 0; j < courses.size(); j++){
                    GRBVar studentClassVar= model.getVarByName("Y" + studentNumber + j + k);
                    Double courseTaken = studentClassVar.get(GRB.DoubleAttr.X);
                    if(courseTaken == 1){
                        String course = courses.get(j).getName();
                        schedule += course + ", ";
                        
                    }
                }
                schedule += "\n";
             }
             return schedule;
         } catch (GRBException ex) {
             Logger.getLogger(GurobiOptimizationManager.class.getName()).log(Level.SEVERE, null, ex);
         }
         return "Operation Out of Bounds";
    }
    
    private GRBModel minimizeCourseSize(ArrayList<Course> courses, ArrayList<Student> students, 
                                    int numberOfSemesters, int allowedNoOfCoursesPerSemester) {
        
        try {
            //initialize model
            GRBEnv env = new GRBEnv("");
            //env.set(GRB.IntParam.OutputFlag, 0);
            GRBModel model = new GRBModel(env);
            
            //create variables
            GRBVar[][][] y = new GRBVar[students.size()][courses.size()][numberOfSemesters];
            for(int i = 0; i < students.size(); i++){
                for(int j = 0; j < courses.size(); j++){
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
            for(int i = 0; i < students.size(); i++){
                for(int j = 0; j < courses.size(); j++){
                    GRBLinExpr courseOnlyOnce = new GRBLinExpr();

                    for(int k = 0; k < numberOfSemesters; k++){
                        GRBVar var= y[i][j][k];
                        courseOnlyOnce.addTerm(1, var);
                    }
                    String courseId = courses.get(j).getId();
                    ArrayList<String> scheduledCourses = students.get(i).getScheduledCourses();
                    boolean isStudentTakingCourse = scheduledCourses.contains(courseId);
                    int rhs = isStudentTakingCourse ? 1 : 0;
                    model.addConstr(courseOnlyOnce, GRB.EQUAL, rhs, null);
                }
            }
            
            //students can only take a certain number of courses each semester
            for(int i = 0; i < students.size(); i++){
                for(int k = 0; k < numberOfSemesters; k++){
                    GRBLinExpr semsesterClassLimit = new GRBLinExpr();
                    for(int j = 0; j < courses.size(); j++){
                        GRBVar var= y[i][j][k];
                        semsesterClassLimit.addTerm(1, var);
                    }
                    
                    model.addConstr(semsesterClassLimit, GRB.LESS_EQUAL, allowedNoOfCoursesPerSemester, null);
                }
            }
            
            //prerequisites
            for(int i = 0; i < students.size(); i++){
                Student student = students.get(i);
                for(int j = 0; j < student.getScheduledCourses().size(); j++){
                    //check if the student is taking any courses which have prerequisites
                    String courseId = student.getScheduledCourses().get(j);
                    switch(courseId){
                        case "1":
                        case "7":
                        case "13":
                        case "16":
                            //find this course's index in the course by id
                            int courseIndex  = 0;
                            Course course = null;
                            for(int c = 0; c < courses.size(); c++){
                                course = courses.get(c);
                                if(course.getId().equals(courseId)){
                                    courseIndex = courses.indexOf(course);
                                    break;
                                }
                            }
                            
                            //get list of prerequisite courses
                            GRBLinExpr lhs = new GRBLinExpr();
                            GRBLinExpr lhs2 = new GRBLinExpr();
                            GRBLinExpr rhs = new GRBLinExpr();
                            //find the index in the course list for each of the
                            //prerequisites for this course, and then build teh
                            //right hand and left had expressions
                            for(int l = 0; l < course.prereqs.size(); l++){
                                for(int k = 0; k < numberOfSemesters; k++){
                                    int prerequisiteIndex = courses.indexOf(course.prereqs.get(l));
                                    rhs.addTerm(1, y[i][courseIndex][k]);
                                    lhs.addTerm(1, y[i][prerequisiteIndex][k]);
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
            for(int j = 0; j < courses.size(); j++){
                for(int k = 0; k < numberOfSemesters; k++){
                    GRBLinExpr capacityLimit = new GRBLinExpr();
                    
                    for(int i = 0; i < students.size(); i++){
                        GRBVar var= y[i][j][k];
                        capacityLimit.addTerm(1, var);
                    }
                    
                    GRBLinExpr rhs = new GRBLinExpr();
                    
                    int rightHandCoefficient = 0;
                    
                    //check to see if the current semester matches the one of the
                    //available semesters of the course if so the rhs coefficient
                    //should be one
                    for(Semester semester: courses.get(j).getSemestersAvailable()){
                        if((k + 1) % Semester.values().length == (semester.ordinal() + 1)){
                            rightHandCoefficient = 1;
                            break;
                        }
                    } 
                    
                    rhs.addTerm(rightHandCoefficient, x);
                    model.addConstr(capacityLimit, GRB.LESS_EQUAL, rhs, null);
                }
            }
            
            model.optimize();
            return model;
            
        } catch (GRBException ex) {
            //ex.printStackTrace();
        }
        
        return null;
    }
}
