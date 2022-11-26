package poofinal.controllers;

import com.itextpdf.text.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import poofinal.application.ActivitiesCellFactor;
import poofinal.application.DriveUpdate;
import poofinal.application.Management;
import poofinal.application.PDFGenerator;
import poofinal.entities.Activities;
import poofinal.entities.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.util.ResourceBundle;

public class CtrlStudentActivitiesView implements Initializable {

    @FXML
    private Label emptyName;
    @FXML
    private Button buttonDone;
    @FXML
    private Button buttonExit;
    @FXML
    private Label emptyNoDisciplines;
    @FXML
    private ListView<Activities> fieldListViewActivities;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());
        emptyName.setText(student.getName());
        try {
            student.loadFilesActivities();
            loadListView(student);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    public void eventButtonDone(ActionEvent actionEvent) throws IOException, DocumentException, GeneralSecurityException {
        Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());
        if(!student.getListActivities().isEmpty()) {
            var pdfPath = Path.of("out/reports/" + student.getName());
            PDFGenerator.pdfSaver(pdfPath, student.getName());
            var file = new File("src\\main\\resources\\files\\studentActivities\\" + student.getName() + "\\");
            if(file.exists() && file.isDirectory()){
                File listFiles[] = file.listFiles();
                for(int i = 0; i < listFiles.length; i++){
                    listFiles[i].delete();
                }
            }
            file.deleteOnExit();
            Management.changeScene("successPDFGenerated.fxml", actionEvent);
        }
        else{
            emptyNoDisciplines.setVisible(true);
        }
    }
    @FXML
    void eventButtonExit(ActionEvent event) throws IOException {
        Management.changeScene("teacherPage.fxml", event);
    }

    @FXML
    void mouseEnteredButtonDone(MouseEvent event) {
        buttonDone.setStyle("-fx-background-radius: 10; -fx-background-color: #004da5");

    }

    @FXML
    void mouseExitedButtonDone(MouseEvent event) {
        buttonDone.setStyle("-fx-background-radius: 10; -fx-background-color: #002651");

    }

    @FXML
    void mouseEnteredButtonExit(MouseEvent event) {
        buttonExit.setStyle("-fx-background-radius: 10; -fx-background-color: #004da5");

    }

    @FXML
    void mouseExitedButtonExit(MouseEvent event) {
        buttonExit.setStyle("-fx-background-radius: 10; -fx-background-color: #002651");
    }

    public void loadListView(Student student) throws IOException, ClassNotFoundException {
        ObservableList<Activities> activitiesObservableList;
        activitiesObservableList = FXCollections.observableArrayList(student.getListActivities());
        fieldListViewActivities.setCellFactory(new ActivitiesCellFactor());
        fieldListViewActivities.setItems(activitiesObservableList);
    }
}

