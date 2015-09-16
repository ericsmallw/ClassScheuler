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
    public int minimizeClassSize(Course[] courses, Student[] students) {
        Random rand = new Random();
        
        return rand.nextInt((100 - 0) + 1) + 0;
    }
}
