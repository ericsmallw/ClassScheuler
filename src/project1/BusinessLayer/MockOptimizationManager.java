/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.BusinessLayer;

import java.util.Random;
import project1.Course;
import project1.Student;


public class MockOptimizationManager implements IOptimizationManager {

    @Override
    public String minimizeClassSize(Course[] courses, Student[] students, 
                                    int numberOfSemesters, int allowedNoOfCoursesPerSemester) {
        Random rand = new Random();
        
        return String.valueOf(rand.nextInt((100 - 0) + 1) + 0);
    }
}
