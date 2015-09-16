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
public class Course {
    private final String name;
    private final int[] prereqIds;
    private final Semester[] semestersAvailable;

    public Course(String name, int[] prereqIds, Semester[] semesterAvailable){
        this.name = name;
        this.prereqIds = prereqIds;
        this.semestersAvailable = semesterAvailable;
    }
    
    public String getName() {
        return name;
    }

    public int[] getPrereqIds() {
        return prereqIds;
    }
    
    public Semester[] getSemestersAvailable() {
        return semestersAvailable;
    }
}
