/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import project1.BusinessLayer.*;
import project1.DataAccess.*;

/**
 *
 * @author ESmallwood
 */
public class Demo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // wire up dependencies
        ICourseRepository courseRepository = new CourseStaticRepository();
        IOptimizationManager optimizationManager = new GurobiOptimizationManager();
        IStudentRepository studentRepository = new StudentFileSystemRepository();
        ICourseManager courseManager = new CourseManager(courseRepository);
        IStudentManager studentManager = new StudentManager(studentRepository);
        CourseScheduler courseScheduler = new CourseScheduler(optimizationManager, courseManager, studentManager);
        
        //courseScheduler.getMinimizedCourseSize("student_schedule.txt", "");
        courseScheduler.getStudentSchedule("student_schedule.txt");
        
        
    }
    
}
