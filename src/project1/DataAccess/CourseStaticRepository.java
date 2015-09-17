/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.DataAccess;

import java.util.ArrayList;
import java.util.HashMap;
import project1.Course;
import project1.Semester;


public class CourseStaticRepository implements ICourseRepository {
    
    @Override
    public ArrayList<Course> getCourses() {
        String[] courseList = CourseStaticData.courseList;
        HashMap<String, String[]> prereqs = getPrerequisites();
        HashMap<String, Semester[]> availablility = getCourseAvailablilty();
        ArrayList<Course> courses = mapCourseData(courseList, prereqs, availablility) ;
        return courses;
    }
    
    @Override
    public HashMap<String, String[]> getPrerequisites(){
        return CourseStaticData.prerequisites;
    }
    
    @Override
    public HashMap<String, Semester[]> getCourseAvailablilty(){
        return CourseStaticData.courseAvailability;
    }
    
    private ArrayList<Course> mapCourseData(String[] courses, 
                                   HashMap<String, String[]> prerequisites,
                                   HashMap<String, Semester[]> availablility) {
        ArrayList<Course> courseArray = new ArrayList<>();
        
        //creating this hashmap so that courses can be looked up quickly when
        //applying prerequisites
        HashMap<String, Course> courseHashMap = new HashMap<>();
        
        //create all courses
        for(int i = 0; i < courses.length; i++){
            ArrayList<Course> coursePrerequisites = new ArrayList<>();
            
            //first item of details is the course id
            //second is the course name
            String[] courseDetails = courses[i].split("\\.");
            Course newCourse = new Course(courseDetails[1], courseDetails[0], availablility.get(courseDetails[0]));
            courseArray.add(newCourse);
            courseHashMap.put(courseDetails[0], newCourse);
        }
        
        //set prerequisites for appropriate courses
        for(String key : prerequisites.keySet()){
            //find course using id
            Course course = courseHashMap.get(key);
            
            for(String prereq : prerequisites.get(key)){
                course.prereqs.add(courseHashMap.get(prereq));
            }
        }
        
        return courseArray;
    }
}
