/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1;

import java.util.ArrayList;

/**
 *
 * @author ESmallwood
 */
public class Student {
    private final ArrayList<String> scheduledCourseIds;

    public Student(ArrayList<String> scheduledCourseIds){
        this.scheduledCourseIds = scheduledCourseIds;
    }
    
    public ArrayList<String> getScheduledCourses() {
        return scheduledCourseIds;
    }
}
