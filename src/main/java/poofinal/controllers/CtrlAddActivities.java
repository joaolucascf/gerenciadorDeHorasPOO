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
import java.util.HashMap;
import java.util.ResourceBundle;

public class CtrlAddActivities implements Initializable {


    public TextField fieldURL;
    @FXML
    private Button buttonCancel;
    @FXML
    private Button buttonOK;
    @FXML
    private ChoiceBox<String> choiceBoxActivities;
    @FXML
    private TextField fieldHours;
    private HashMap<String, Activities> activitiesBuffer = Management.getActivitiesBuffer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeChoiceBoxes();
    }

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

    @FXML
    void mouseEnteredButtonCancel(MouseEvent event) {
        buttonCancel.setStyle("-fx-background-radius: 10; -fx-background-color: #004da5");
    }

    @FXML
    void mouseExitedButtonCancel(MouseEvent event) {
        buttonCancel.setStyle("-fx-background-radius: 10; -fx-background-color: #002651");
    }

    @FXML
    void mouseEnteredButtonOK(MouseEvent event) {
        buttonOK.setStyle("-fx-background-radius: 10; -fx-background-color: #004da5");
    }

    @FXML
    void mouseExitedButtonOK(MouseEvent event) {
        buttonOK.setStyle("-fx-background-radius: 10; -fx-background-color: #002651");
    }

    @FXML
    void eventMouseClicked(MouseEvent mouseEvent) {
        choiceBoxActivities.setOnAction((event)->{
            for(String keyId : activitiesBuffer.keySet()){
                if(choiceBoxActivities.getValue().equals(activitiesBuffer.get(keyId).getDescription())){
                    fieldHours.setText(Management.getActivitiesBuffer().get(keyId).getHours());
                }
            }
        });
    }

    private void initializeChoiceBoxes(){
        choiceBoxActivities.setValue(" ");
        for(String keyId :  activitiesBuffer.keySet()){
            choiceBoxActivities.getItems().add(activitiesBuffer.get(keyId).getDescription());
        }
    }

    public void readerInformation() throws IOException {
        Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());
        for(String keyId : activitiesBuffer.keySet()) {
            if(choiceBoxActivities.getValue().equals(activitiesBuffer.get(keyId).getDescription())){
                student.setActivities(activitiesBuffer.get(keyId), fieldURL.getText());
            }
        }
    }

    private boolean validateEntries(){
        refreshValidationFields();
        if(choiceBoxActivities.getValue().equals(" ")) {
            choiceBoxActivities.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: red; -fx-background-color: white");
            return false;
        }
        if(fieldURL.getText().isEmpty()){
            fieldURL.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: red; -fx-background-color: white");
            return false;
        }
        return true;
    }

    private void refreshValidationFields(){
        choiceBoxActivities.setStyle(null);
    }
}
