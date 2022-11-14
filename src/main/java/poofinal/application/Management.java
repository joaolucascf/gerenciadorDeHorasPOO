package poofinal.application;

import poofinal.entities.Student;
import java.util.HashMap;


public class Management {
    private static HashMap<String, Student> studentBuffer = new HashMap<String, Student>();
    public static void addStudent(Student newStudent){
        studentBuffer.put(newStudent.getCPF(), newStudent);
    }
}
