/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.DataAccess;

import java.util.HashMap;
import project1.Course;
import project1.Semester;

/**
 *
 * @author ESmallwood
 */
public interface ICourseRepository {
    Course[] getCourses();
    HashMap<Integer, Integer[]> getPrerequisites();
    HashMap<Integer, Semester[]> getCourseAvailablilty();
}
