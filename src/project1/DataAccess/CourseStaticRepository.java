/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.DataAccess;

import java.util.HashMap;
import project1.Course;
import project1.Semester;


public class CourseStaticRepository implements ICourseRepository {
    
    @Override
    public Course[] getCourses() {
        String[] courseList = CourseStaticData.courseList;
        HashMap<Integer, Integer[]> prereqs = getPrerequisites();
        HashMap<Integer, Semester[]> availablility = getCourseAvailablilty();
        Course[] courses = mapCourseData(courseList, prereqs, availablility) ;
        return courses;
    }
    
    @Override
    public HashMap<Integer, Integer[]> getPrerequisites(){
        return CourseStaticData.prerequisites;
    }
    
    @Override
    public HashMap<Integer, Semester[]> getCourseAvailablilty(){
        return CourseStaticData.courseAvailability;
    }
    
    private Course[] mapCourseData(String[] courses, 
                                   HashMap<Integer, Integer[]> prerequisites,
                                   HashMap<Integer, Semester[]> availablility) {
        Course[] courseArray = new Course[courses.length];
        
        for(int i = 0; i < courses.length; i++){
            int[] coursePrerequisites = new int[]{};
            
            //check for prerequisites
            if(prerequisites.containsKey(i)){
                //convert Integer[] to in=[]
                int numOfPrereqs = prerequisites.get(i).length;
                coursePrerequisites = new int[numOfPrereqs];
                for(int j = 0; j < numOfPrereqs; j++){
                    coursePrerequisites[j] = prerequisites.get(i)[j];
                }
            }
                    
            courseArray[i] = new Course(courses[i], coursePrerequisites, availablility.get(i));
        }
        
        return courseArray;
    }
}
