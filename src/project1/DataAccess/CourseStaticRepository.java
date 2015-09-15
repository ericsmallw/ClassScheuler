/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.DataAccess;

import java.util.HashMap;


public class CourseStaticRepository implements ICourseRepository {
    private final String[] courseList = {
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
    
    private final HashMap<Integer, Integer[]> prerequisites = new HashMap<>();
    
    {
        prerequisites.put(15, new Integer[]{3});
        prerequisites.put(0, new Integer[]{11});
        prerequisites.put(12, new Integer[]{8});
        prerequisites.put(6, new Integer[]{2});
    }
    
    public String[] getCourses() {
        return courseList;
    }
    
    public HashMap<Integer, Integer[]> getPrerequisites(){
        return prerequisites;
    }
    
}
