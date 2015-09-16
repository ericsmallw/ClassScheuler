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
    
    public Course(String name, int[] prereqIds){
        this.name = name;
        this.prereqIds = prereqIds;
    }
    
    public String getName() {
        return name;
    }

    public int[] getPrereqIds() {
        return prereqIds;
    }
}
