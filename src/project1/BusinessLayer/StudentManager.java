/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.BusinessLayer;

import project1.Course;
import project1.DataAccess.IStudentRepository;
import project1.Student;


public class StudentManager implements IStudentManager {
    private final IStudentRepository studentRepository;
    
    public StudentManager(IStudentRepository studentRepository){
           this.studentRepository = studentRepository;    
    }
    
    @Override
    public Student[] getStudents(String data, Course[] courses){
        return studentRepository.getStudents(data);
    }
}
