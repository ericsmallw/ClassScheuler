/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.DataAccess;

import java.util.HashMap;
import project1.Semester;

/**
 *
 * @author ESmallwood
 */
public class CourseStaticData {
    protected static final String[] courseList = {
        "1.CS 6210 Advanced Operating Systems",
        "2.CS 6250Computer Networks",
        "3.CS 6300 Software Development Process",
        "4.CS7641 Machine Learning",
        "5.CS 6290 High Performance Computer Architecture",
        "6.CS 6310 Software Architecture and Design",
        "7.CS 6440 Intro toHealth Informatics",
        "8.CS 6505 Computability, Complexity and Algorithms",
        "9.CS 7637 Knowledge-Based Artificial Intelligence, Cognitive Systems",
        "10.CS 4495 Computer Vision",
        "11.CS 6475 Computational Photography",
        "12.CS 8803-002 Introduction to Operating Systems",
        "13.CS 8803-001 Artificial Intelligence for Robotics",
        "14.CS 6035 Introduction to InformationSecurity",
        "15.CSE 6220 High-Performance Computing",
        "16.CS 7646 MachineLearning for Trading",
        "17.CS 8803 Special Topics: Reinforcement Learning",
        "18.CSE 8803 Special Topics: Big Data"
    };
    
    protected static final HashMap<String, String[]> prerequisites = new HashMap<>();
    protected static final HashMap<String, Semester[]> courseAvailability = new HashMap<>();
    
    static{
        prerequisites.put("16", new String[]{"4"});
        prerequisites.put("1", new String[]{"12"});
        prerequisites.put("13", new String[]{"9"});
        prerequisites.put("7", new String[]{"3"});
        
        courseAvailability.put("1", new Semester[]{Semester.Fall});
        courseAvailability.put("2", new Semester[]{Semester.Fall,Semester.Spring,Semester.Summer});
        courseAvailability.put("3", new Semester[]{Semester.Fall,Semester.Spring,Semester.Summer});
        courseAvailability.put("4", new Semester[]{Semester.Fall,Semester.Spring,Semester.Summer});
        courseAvailability.put("5", new Semester[]{Semester.Spring});
        courseAvailability.put("6", new Semester[]{Semester.Fall,Semester.Spring,Semester.Summer});
        courseAvailability.put("7", new Semester[]{Semester.Fall});
        courseAvailability.put("8", new Semester[]{Semester.Fall,Semester.Spring,Semester.Summer});
        courseAvailability.put("9", new Semester[]{Semester.Fall,Semester.Spring,Semester.Summer});
        courseAvailability.put("10", new Semester[]{Semester.Spring});
        courseAvailability.put("11", new Semester[]{Semester.Fall});
        courseAvailability.put("12", new Semester[]{Semester.Fall,Semester.Spring,Semester.Summer});
        courseAvailability.put("13", new Semester[]{Semester.Fall,Semester.Spring,Semester.Summer});
        courseAvailability.put("14", new Semester[]{Semester.Spring});
        courseAvailability.put("15", new Semester[]{Semester.Fall});
        courseAvailability.put("16", new Semester[]{Semester.Spring});
        courseAvailability.put("17", new Semester[]{Semester.Fall});
        courseAvailability.put("18", new Semester[]{Semester.Spring});
    }
}
