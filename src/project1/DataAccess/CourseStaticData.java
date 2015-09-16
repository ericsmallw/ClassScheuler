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
        "CS 6210 Advanced Operating Systems",
        "CS 6250Computer Networks",
        "CS 6300 Software Development Process 4",
        "CS7641 Machine Learning",
        "CS 6290 High Performance Computer Architecture",
        "CS 6310 Software Architecture and Design 7",
        "CS 6440 Intro toHealth Informatics",
        "CS 6505 Computability, Complexity and Algorithms",
        "CS 7637 Knowledge-Based Artificial Intelligence, Cognitive Systems",
        "CS 4495 Computer Vision",
        "CS 6475 Computational Photography",
        "CS 8803-002 Introduction to Operating Systems",
        "CS 8803-001Artificial Intelligence for Robotics",
        "CS 6035 Introduction to InformationSecurity",
        "CSE 6220 High-Performance Computing",
        "CS 7646 MachineLearning for Trading",
        "CS 8803 Special Topics: Reinforcement Learning",
        "CSE 8803 Special Topics: Big Data"
    };
    
    protected static final HashMap<Integer, Integer[]> prerequisites = new HashMap<>();
    protected static final HashMap<Integer, Semester[]> courseAvailability = new HashMap<>();
    
    static{
        prerequisites.put(15, new Integer[]{3});
        prerequisites.put(0, new Integer[]{11});
        prerequisites.put(12, new Integer[]{8});
        prerequisites.put(6, new Integer[]{2});
        
        courseAvailability.put(0, new Semester[]{Semester.Fall});
        courseAvailability.put(1, new Semester[]{Semester.Fall,Semester.Spring,Semester.Summer});
        courseAvailability.put(2, new Semester[]{Semester.Fall,Semester.Spring,Semester.Summer});
        courseAvailability.put(3, new Semester[]{Semester.Fall,Semester.Spring,Semester.Summer});
        courseAvailability.put(4, new Semester[]{Semester.Spring});
        courseAvailability.put(5, new Semester[]{Semester.Fall,Semester.Spring,Semester.Summer});
        courseAvailability.put(6, new Semester[]{Semester.Fall});
        courseAvailability.put(7, new Semester[]{Semester.Fall,Semester.Spring,Semester.Summer});
        courseAvailability.put(8, new Semester[]{Semester.Fall,Semester.Spring,Semester.Summer});
        courseAvailability.put(9, new Semester[]{Semester.Spring});
        courseAvailability.put(10, new Semester[]{Semester.Fall});
        courseAvailability.put(11, new Semester[]{Semester.Fall,Semester.Spring,Semester.Summer});
        courseAvailability.put(12, new Semester[]{Semester.Fall,Semester.Spring,Semester.Summer});
        courseAvailability.put(13, new Semester[]{Semester.Spring});
        courseAvailability.put(14, new Semester[]{Semester.Fall});
        courseAvailability.put(15, new Semester[]{Semester.Spring});
        courseAvailability.put(16, new Semester[]{Semester.Fall});
        courseAvailability.put(17, new Semester[]{Semester.Spring});
    }
}
