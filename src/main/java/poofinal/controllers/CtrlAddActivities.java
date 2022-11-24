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
        for(String keyId : Management.getActivitiesBuffer().keySet()){
            if(choiceBoxActivities.getValue().equals(Management.getActivitiesBuffer().get(keyId).getDescription())){
                fieldHours.setText(Management.getActivitiesBuffer().get(keyId).getHours());
            }
        }
    }
    @FXML
    void eventMovedMouse(MouseEvent event) {
        for(String keyId : Management.getActivitiesBuffer().keySet()){
            if(choiceBoxActivities.getValue().equals(Management.getActivitiesBuffer().get(keyId).getDescription())){
                fieldHours.setText(Management.getActivitiesBuffer().get(keyId).getHours());
            }
        }
    }
    @FXML
    void eventChoiceBoxKeyReleased(KeyEvent event) {
        for(String keyId : Management.getActivitiesBuffer().keySet()){
            if(choiceBoxActivities.getValue().equals(Management.getActivitiesBuffer().get(keyId).getDescription())){
                fieldHours.setText(Management.getActivitiesBuffer().get(keyId).getHours());
            }
        }
    }

    private void initializeChoiceBoxes(){
        choiceBoxActivities.setValue(" ");
        for(String keyId : Management.getActivitiesBuffer().keySet()){
            choiceBoxActivities.getItems().add(Management.getActivitiesBuffer().get(keyId).getDescription());
        }
    }

    public void readerInformation() throws IOException {
        Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());
        for(String keyId : Management.getActivitiesBuffer().keySet()) {
            if(choiceBoxActivities.getValue().equals(Management.getActivitiesBuffer().get(keyId).getDescription())){
                student.setActivities(Management.getActivitiesBuffer().get(keyId));
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
