/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

/**
 *
 * @author ESmallwood
 */
public class Student {
    private final Course[] scheduledCourses;

    public Student(Course[] scheduledCourses){
        this.scheduledCourses = scheduledCourses;
    }
    
    public Course[] getScheduledCourses() {
        return scheduledCourses;
    }
}
