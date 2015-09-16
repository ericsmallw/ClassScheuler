/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.BusinessLayer;

import java.util.ArrayList;
import project1.Course;
import project1.DataAccess.IStudentRepository;
import project1.Student;


public class StudentManager implements IStudentManager {
    private final IStudentRepository studentRepository;
    
    public StudentManager(IStudentRepository studentRepository){
           this.studentRepository = studentRepository;    
    }
    
    public Student[] getStudents(String data, Course[] courses){
        String studentData = studentRepository.getStudents(data);
        return createStudents(studentData, courses);
    }
    
    private Student[] createStudents(String studentData, Course[] courses){
        ArrayList<Student> students = new ArrayList<>();
        String[] studentsBySchedule = studentData.split("\\r?\\n");
        
        for(int i = 0; i < studentsBySchedule.length; i++){
            ArrayList<Course> scheduledCourses = new ArrayList<>();
            String[] courseNumbers = studentsBySchedule[i].split("\\.");
            for(int j = 0; j < courseNumbers.length; j++){
                try{
                    int courseIndex = Integer.parseInt(courseNumbers[j].trim()) - 1;
                    scheduledCourses.add(courses[courseIndex]);
                }catch(NumberFormatException nfe){
                    //continue as this is not a number
                }
            }
            
            if(scheduledCourses.size() > 0){
            Course[] scheduledCoursesArr = new Course[scheduledCourses.size()];
            scheduledCourses.toArray(scheduledCoursesArr);
            students.add(new Student(scheduledCoursesArr));
            }
        }
        
        Student[] studentArr = new Student[students.size()];
        students.toArray(studentArr);
        return studentArr;
    }
}
