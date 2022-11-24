package poofinal.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
    private HashMap<String, Activities> activitiesBuffer = Management.getActivitiesBuffer();

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initializeChoiceBox();
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
            choiceBoxActivities.setStyle("-fx-border-color:red");
            return false;
        }
        if(fieldChangeHours.getText().isEmpty()){
            fieldChangeHours.setStyle("-fx-border-color:red");
            emptyChangeHours.setVisible(true);
            return false;
        }
        return true;
    }

    private void refreshValidationFields(){
        choiceBoxActivities.setStyle(null);
        fieldChangeHours.setStyle(null);
    }


}
