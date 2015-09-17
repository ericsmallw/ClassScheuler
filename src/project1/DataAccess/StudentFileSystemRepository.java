/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project1.DataAccess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import project1.Student;


public class StudentFileSystemRepository implements IStudentRepository {

    @Override
    public ArrayList<Student> getStudents(String data) {
        BufferedReader reader;
        StringBuilder sb = new StringBuilder();
        try {
            reader = new BufferedReader(new FileReader(data));
            
            String line;

            while((line = reader.readLine()) != null){
                sb.append(line);
                sb.append("\n");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String studentData = sb.toString();
        ArrayList<Student> students = mapStudentData(studentData);
        return students;
    }
    
    private ArrayList<Student> mapStudentData(String studentData){
        ArrayList<Student> students = new ArrayList<>();
        String[] studentsBySchedule = studentData.split("\\r?\\n");
        
        for(int i = 0; i < studentsBySchedule.length; i++){
            ArrayList<String> scheduledCourses = new ArrayList<>();
            String[] courseNumbers = studentsBySchedule[i].split("\\.");
            for(int j = 0; j < courseNumbers.length; j++){
                try{
                    //check that this is a number
                    Integer.parseInt(courseNumbers[j].trim());
                    scheduledCourses.add(courseNumbers[j].trim());
                }catch(NumberFormatException nfe){
                    //continue as this is not a number
                }
            }
            
            if(scheduledCourses.size() > 0){
                students.add(new Student(scheduledCourses));
            }
        }
        
        return students;
    }
    
}
