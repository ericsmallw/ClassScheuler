/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.BusinessLayer;

import java.util.HashMap;
import project1.Course;
import project1.DataAccess.ICourseRepository;


public class CourseManager implements ICourseManager {
    private final ICourseRepository courseRepository;
    
    public CourseManager(ICourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }
    
    public Course[] getCourses() {
        String[] courses = courseRepository.getCourses();
        HashMap<Integer, Integer[]> prerequisites = courseRepository.getPrerequisites();
        
        return createCourses(courses, prerequisites);
    }

    private Course[] createCourses(String[] courses, HashMap<Integer, Integer[]> prerequisites) {
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
                    
            courseArray[i] = new Course(courses[i], coursePrerequisites);
        }
        
        return courseArray;
    }
    
}
