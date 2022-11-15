package poofinal.application;

import poofinal.entities.Student;
import java.io.*;
import java.util.HashMap;


public class Management {
    private static HashMap<String, Student> studentBuffer = new HashMap<String, Student>();
    private static HashMap<String, File> registrationBuffer = new HashMap<String, File>();
    public static void addStudent(Student student) throws IOException {
        File file = new File(student.getName().concat(".dat"));
        FileOutputStream outFile = new FileOutputStream(file);
        ObjectOutputStream outStream = new ObjectOutputStream(outFile);
        outStream.writeObject(student);
        outStream.close();
        outFile.close();
        registrationBuffer.put(student.getMatriculation(), file);
    }

    public static boolean checkLoginStudent(String matriculation, String password) throws IOException, ClassNotFoundException {
        Student student;
        for(String keyMatriculation : registrationBuffer.keySet()){
            if (keyMatriculation.equals(matriculation)) {
                File file = registrationBuffer.get(keyMatriculation);
                FileInputStream inFile = new FileInputStream(file);
                ObjectInputStream inStream = new ObjectInputStream(inFile);
                student = (Student) inStream.readObject();
                if(student.getPassword().equals(password)){
                    studentBuffer.put(matriculation, student);
                    return true;
                }
            }
        }
        return false;
    }
}
