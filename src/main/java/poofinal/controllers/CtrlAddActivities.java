package poofinal.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import poofinal.application.Management;
import poofinal.entities.Activities;
import poofinal.entities.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CtrlAddActivities implements Initializable {

    @FXML
    private Button buttonCancel;

    @FXML
    private Button buttonOK;

    @FXML
    private ChoiceBox<String> choiceBoxActivities;

    @FXML
    private TextField fieldHours;

    @FXML
    void eventButtonCancel(ActionEvent event) throws IOException {
        Management.changeScene("studentPage.fxml", event);
    }

    @FXML
    void eventButtonOK(ActionEvent event) throws IOException {
        if(validateEntries()) {
            readerInformation();
            Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());
            Management.createActivitiesFile(student);
            Management.createSavingFile(student);
            Management.changeScene("studentPage.fxml", event);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeChoiceBoxes();
    }

    @FXML
    void eventChoiceBoxEntered(MouseEvent event){
        for(Activities activities : Activities.values()){
            if(choiceBoxActivities.getValue().equals(activities.getDescription())) {
                fieldHours.setText(activities.getHours());
            }
        }
    }
    @FXML
    void eventMovedMouse(MouseEvent event) {
        for(Activities activities : Activities.values()){
            if(choiceBoxActivities.getValue().equals(activities.getDescription())) {
                fieldHours.setText(activities.getHours());
            }
        }
    }
    @FXML
    void eventChoiceBoxKeyReleased(KeyEvent event) {
        for(Activities activities : Activities.values()){
            if(choiceBoxActivities.getValue().equals(activities.getDescription())) {
                fieldHours.setText(activities.getHours());
            }
        }
    }

    private void initializeChoiceBoxes(){
        choiceBoxActivities.setValue(" ");
        for(Activities activities : Activities.values()) {
            choiceBoxActivities.getItems().add(activities.getDescription());
        }
    }

    public void readerInformation() throws IOException {
        Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());
        for(Activities activities : Activities.values()){
            if(choiceBoxActivities.getValue().equals(activities.getDescription())) {
                student.setActivities(activities);
            }
        }

    }

    private boolean validateEntries(){
        refreshValidationFields();
        if(choiceBoxActivities.getValue().equals(" ")) {
            choiceBoxActivities.setStyle("-fx-border-color:red");
            return false;
        }
        return true;
    }

    private void refreshValidationFields(){
        choiceBoxActivities.setStyle(null);
    }
}