package poofinal.application;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.shape.Path;
import javafx.stage.Stage;
import poofinal.entities.Student;
import java.io.*;
import java.util.HashMap;

public class Management {
    private static HashMap<String, Student> studentBuffer = new HashMap<String, Student>();
    private static HashMap<String, File> registrationBuffer = new HashMap<String, File>();
    private static Scene scene;

    private static String keyMatriculation;

    public static void setKeyMatriculation(String keyMatriculation) {
        Management.keyMatriculation = keyMatriculation;
    }

    public static String getKeyMatriculation() {
        return keyMatriculation;
    }

    public static HashMap<String, Student> getStudentBuffer() {
        return studentBuffer;
    }

    public static void addStudent(Student student) throws IOException {
        File file = new File("src\\main\\resources\\files\\" + student.getName().concat(".dat"));
        ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file));
        outStream.writeObject(student);
        outStream.close();
        registrationBuffer.put(student.getMatriculation(), file);
    }

    public static boolean checkLoginStudent(String matriculation, String password) throws IOException, ClassNotFoundException {
        Student student;
        for (String keyMatriculation : registrationBuffer.keySet()) {
            if (keyMatriculation.equals(matriculation)) {
                File file = registrationBuffer.get(keyMatriculation);
                ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(file));
                student = (Student) inStream.readObject();
                inStream.close();
                if (student.getPassword().equals(password)) {
                    studentBuffer.put(student.getMatriculation(), student);
                    Management.setKeyMatriculation(keyMatriculation);
                    return true;
                }
            }
        }
        return false;
    }

    public static void changeScene(String nameFXML, Event event) throws IOException {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(FXMLLoader.load(Management.class.getResource(nameFXML)));
        stage.setScene(scene);
        stage.show();
    }

    public static void loadFiles() throws IOException, ClassNotFoundException {
        File path = new File("src\\main\\resources\\files\\");
        if (path.exists() && path.isDirectory()) {
            File listFiles[] = path.listFiles();
            for(int i = 0; i < listFiles.length; i++){
                //System.out.println("File " + (i+1) + ": " + path + listFiles[i].getName());
                ObjectInputStream inStream = new ObjectInputStream(new FileInputStream("src\\main\\resources\\files\\" + listFiles[i].getName()));
                Student student = (Student) inStream.readObject();
                inStream.close();
                registrationBuffer.put(student.getMatriculation(), listFiles[i]);
            }
        }
    }
}
