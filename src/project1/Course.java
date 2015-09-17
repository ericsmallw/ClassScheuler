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
public class Course {
    private final String name;
    private final String id;
    private final Semester[] semestersAvailable;
    
    public ArrayList<Course> prereqs = new ArrayList<>();

    public Course(String name, String id, Semester[] semesterAvailable){
        this.name = name;
        this.id = id;
        this.semestersAvailable = semesterAvailable;
    }
    
    public String getName() {
        return name;
    }
    
    public  String getId(){
        return id;
    }
    
    public Semester[] getSemestersAvailable() {
        return semestersAvailable;
    }
}
