/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.DataAccess;

import java.util.ArrayList;
import project1.Student;

/**
 *
 * @author ESmallwood
 */
public interface IStudentRepository {
    ArrayList<Student> getStudents(String data);
}
