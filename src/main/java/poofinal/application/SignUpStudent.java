package poofinal.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import poofinal.entities.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SignUpStudent implements Initializable {

    @FXML
    private Button buttonCancel;

    @FXML
    private TextField fieldEmail;

    @FXML
    private TextField fieldMatriculation;

    @FXML
    private ChoiceBox<String> choiceBoxCourse;

    @FXML
    private TextField fieldName;

    @FXML
    void EventButtonCancel(ActionEvent event) {
        Stage stage = (Stage)buttonCancel.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Student.Course course : Student.Course.values()){
            choiceBoxCourse.getItems().add(course.getDescription());
        }
    }

}




