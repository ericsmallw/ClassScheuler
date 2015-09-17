/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.ArrayList;
import project1.BusinessLayer.ICourseManager;
import project1.BusinessLayer.IOptimizationManager;
import project1.BusinessLayer.IStudentManager;

/**
 *
 * @author ESmallwood
 */
public class CourseScheduler {
    private final IOptimizationManager optimizationManger;
    private final ICourseManager courseManager;
    private final IStudentManager studentManager;
    
    public CourseScheduler(IOptimizationManager optimizationManger, 
                           ICourseManager courseManager, 
                           IStudentManager studentManager){
        this.optimizationManger = optimizationManger;
        this.courseManager = courseManager;
        this.studentManager = studentManager;
    }
    
    public void getMinimizedCourseSize(String studentData, String courseData){
        ArrayList<Course> courses = courseManager.getCourses();
        ArrayList<Student> students = studentManager.getStudents(studentData);
        String classSize = optimizationManger.getMinimizedCourseSize(courses, students, 12, 2);
        System.out.println("X = " + classSize);
    };
    
    public void getStudentSchedule(String studentData){
        ArrayList<Course> courses = courseManager.getCourses();
        ArrayList<Student> students = studentManager.getStudents(studentData);
        String schedule = optimizationManger.getCourseScheduleForStudent(courses, students, 12, 2, 1);
        System.out.println(schedule);
    }
}
