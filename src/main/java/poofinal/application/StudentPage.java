package poofinal.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import poofinal.entities.Pessoa;
import poofinal.entities.Student;

import java.io.IOException;
import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class StudentPage implements Initializable {

    @FXML
    private Button buttonActivities;

    @FXML
    private Button buttonExit;

    @FXML
    private Label emptyCourse;

    @FXML
    private Label emptyGraduationForecast;

    @FXML
    private Label emptyJoined;

    @FXML
    private Label emptyMatriculation;

    @FXML
    private Label emptyName;

    @FXML
    private ListView<?> fieldListView;

    @FXML
    void eventButtonActivities(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());
        emptyName.setText(student.getName());
        emptyCourse.setText(student.getCourse().getDescription());
        emptyMatriculation.setText(student.getMatriculation());
        emptyJoined.setText(student.getJoined());
        emptyGraduationForecast.setText(student.getGraduationForecast());
    }

    @FXML
    void eventButtonExit(ActionEvent event) throws IOException {
        Management.changeScene("mainMenu.fxml", event);
    }
}
