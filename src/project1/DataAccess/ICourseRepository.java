/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.DataAccess;

import java.util.HashMap;

/**
 *
 * @author ESmallwood
 */
public interface ICourseRepository {
    String[] getCourses();
    HashMap<Integer, Integer[]> getPrerequisites();
}
