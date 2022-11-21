package poofinal.application;

import javafx.application.Application;
import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import poofinal.entities.Student;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
        //File file = new File("src\\main\\resources\\files\\studentRegistration\\" + student.getName().concat(".dat"));
        Path path = Path.of("src\\main\\resources\\files\\studentRegistration\\");
        if(!Files.exists(path)) {
            Files.createDirectories(path);
        }
        File file = new File(path + "\\" + student.getName().concat(".dat"));
        ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file));
        outStream.writeObject(student);
        outStream.close();
        registrationBuffer.put(student.getMatriculation(), file);
    }

    public static boolean checkLoginStudent(String matriculation, String password) throws IOException, ClassNotFoundException {
        for (String keyMatriculation : registrationBuffer.keySet()) {
            if (keyMatriculation.equals(matriculation)) {
                File file = registrationBuffer.get(keyMatriculation);
                ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(file));
                Student student = (Student) inStream.readObject();
                inStream.close();
                if (student.getPassword().equals(password)) {
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

    public static void loadFiles() throws ClassNotFoundException, IOException {
        File path = new File("src\\main\\resources\\files\\studentRegistration\\");
        if (path.exists() && path.isDirectory()) {
            File listFiles[] = path.listFiles();
            for(int i = 0; i < listFiles.length; i++){
                ObjectInputStream inStream = new ObjectInputStream(new FileInputStream("src\\main\\resources\\files\\studentRegistration\\" + listFiles[i].getName()));
                Student student = (Student) inStream.readObject();
                inStream.close();
                studentBuffer.put(student.getMatriculation(), student);
                registrationBuffer.put(student.getMatriculation(), listFiles[i]);
            }
        }
    }

    public static void createSavingFile(Student student) throws IOException {
        Path path = Path.of("src\\main\\resources\\files\\studentSavingFiles\\");
        if(!Files.exists(path)) {
            Files.createDirectories(path);
        }
        File file = new File(path + "\\" + student.getName() + "_" + student.getMatriculation() + ".dat");
        DataOutputStream outStream = new DataOutputStream(new FileOutputStream(file));
        outStream.writeUTF(student.getName());
        outStream.writeUTF(student.getMatriculation());
        outStream.writeUTF(student.getEmail());
        outStream.writeUTF(student.getCourse().getDescription());
        outStream.writeUTF(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        outStream.close();
    }
}
