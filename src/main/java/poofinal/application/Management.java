package poofinal.application;

import poofinal.entities.Pessoa;

import java.util.ArrayList;
import java.util.List;

public class Management {
    static private List<Pessoa> listStudents = new ArrayList<Pessoa>();

    static public void addStudent(Pessoa student){
        listStudents.add(student);
    }

    static public void printStudent(){
        for(Pessoa student : listStudents){
            System.out.println(student);
        }
    }
}
