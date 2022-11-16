package poofinal.application;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import poofinal.entities.Student;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Management {
    private static HashMap<String, File> registrationBuffer = new HashMap<String, File>();
    private static Scene scene;


    public static void addStudent(Student student) throws IOException {
        String studentFilePath = "src\\main\\resources\\Files\\" + student.getMatriculation().concat(".dat");
        String regFilePath = "src\\main\\resources\\Files\\mapping\\HashMap.dat";
        File studentFile = new File(studentFilePath);
        ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(studentFile));
        outStream.writeObject(student);
        outStream.close();
        System.out.println("Dados do estudantes salvos em salvo em: "+ studentFile.getAbsolutePath());
        registrationBuffer.put(student.getMatriculation(), studentFile);
        File regFile = new File(regFilePath);
        ObjectOutputStream regObjFile = new ObjectOutputStream(new FileOutputStream(regFile));
        for(Map.Entry<String, File> register : registrationBuffer.entrySet()){
            regObjFile.writeUTF(register.getKey());
            regObjFile.writeObject(studentFile);
        }
        regObjFile.close();
    }

    public static boolean checkLoginStudent(String matriculation, String password) throws IOException, ClassNotFoundException {
        Student student;
        if(loadFiles()) {
            for (String keyMatriculation : registrationBuffer.keySet()) {
                if (keyMatriculation.equals(matriculation)) {
                    File file = registrationBuffer.get(keyMatriculation);
                    ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(file));
                    student = (Student) inStream.readObject();
                    inStream.close();
                    if (student.getPassword().equals(password)) {
                        return true;
                    }
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

    public static boolean loadFiles() throws IOException, ClassNotFoundException {
        String regFilePath = "src\\main\\resources\\Files\\mapping\\HashMap.dat";
        File regFile = new File(regFilePath);
        ObjectInputStream regObjFile = new ObjectInputStream(new FileInputStream(regFile));
        try{
            for(;;){
                registrationBuffer.put(regObjFile.readUTF(),(File)regObjFile.readObject());
            }
        }catch (EOFException e){
            System.out.println("Registros carregados com sucesso.");
            return true;
        }catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
