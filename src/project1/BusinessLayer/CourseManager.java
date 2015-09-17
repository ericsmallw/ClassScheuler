/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.BusinessLayer;

import project1.Course;
import project1.DataAccess.ICourseRepository;


public class CourseManager implements ICourseManager {
    private final ICourseRepository courseRepository;
    
    public CourseManager(ICourseRepository courseRepository){
        this.courseRepository = courseRepository;
    }
    
    @Override
    public Course[] getCourses() {
        return  courseRepository.getCourses();
    }
}
