/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.Hashtable;
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
    
    public CourseScheduler(IOptimizationManager optimizationManger, ICourseManager courseManager, IStudentManager studentManager){
        this.optimizationManger = optimizationManger;
        this.courseManager = courseManager;
        this.studentManager = studentManager;
    }
    
    public int minimizeCourseSize(String studentData, String courseData){
        Course[] courses = courseManager.getCourses();
        Student[] students = studentManager.getStudents();
        return 0;
    };
}
