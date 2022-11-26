package poofinal.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import poofinal.application.Management;
import poofinal.entities.Activities;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class CtrlEditorTable implements Initializable {

    @FXML
    private ChoiceBox<String> choiceBoxActivities;
    @FXML
    private TextField fieldChangeHours;
    @FXML
    private Label emptyChangeHours;
    @FXML
    private Button buttonCancel;
    @FXML
    private Button buttonOK;
    private HashMap<String, Activities> activitiesBuffer = Management.getActivitiesBuffer();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeChoiceBox();
    }

    @FXML
    void eventButtonCancel(ActionEvent event) throws IOException {
        Management.changeScene("teacherPage.fxml", event);
    }

    @FXML
    void eventButtonChange(ActionEvent event) throws IOException {
        if(validateEntries()){
            for(String keyId : activitiesBuffer.keySet()){
                if(choiceBoxActivities.getValue().equals(activitiesBuffer.get(keyId).getDescription())){
                    activitiesBuffer.get(keyId).setHours(fieldChangeHours.getText());
                }
            }
            Management.changeTableActivities();
            Management.changeScene("teacherPage.fxml", event);
        }
    }

    @FXML
    void eventMouseClicked(MouseEvent mouseEvent) {
        choiceBoxActivities.setOnAction((event)->{
            for(String keyId : activitiesBuffer.keySet()){
                if(choiceBoxActivities.getValue().equals(activitiesBuffer.get(keyId).getDescription())){
                    fieldChangeHours.setText(Management.getActivitiesBuffer().get(keyId).getHours());
                }
            }
        });
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

    public void initializeChoiceBox(){
        choiceBoxActivities.setValue(" ");
        for(String keyId : activitiesBuffer.keySet()){
            choiceBoxActivities.getItems().add(activitiesBuffer.get(keyId).getDescription());
        }
    }

    private boolean validateEntries(){
        refreshValidationFields();
        if(choiceBoxActivities.getValue().equals(" ")) {
            choiceBoxActivities.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: red; -fx-background-color: white");
            fieldChangeHours.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-background-color: white");
            return false;
        }
        if(fieldChangeHours.getText().isEmpty()){
            fieldChangeHours.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: red; -fx-background-color: white");
            emptyChangeHours.setVisible(true);
            return false;
        }
        return true;
    }

    private void refreshValidationFields(){
        fieldChangeHours.setStyle(null);
    }


}
