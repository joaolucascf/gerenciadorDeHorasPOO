package poofinal.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private Stage stage;
    private Scene scene;
    @FXML
    void EventButtonCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(FXMLLoader.load(getClass().getResource("mainMenu.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(Student.Course course : Student.Course.values()){
            choiceBoxCourse.getItems().add(course.getDescription());
        }
    }
}




