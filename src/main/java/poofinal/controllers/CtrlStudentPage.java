package poofinal.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import poofinal.application.Management;
import poofinal.entities.Activities;
import poofinal.entities.Student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CtrlStudentPage implements Initializable {

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
    private ListView<Activities> fieldListView;

    @FXML
    void eventButtonActivities(ActionEvent event) throws IOException {
        Management.changeScene("addActivities.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());
        emptyName.setText(student.getName());
        emptyCourse.setText(student.getCourse().getDescription());
        emptyMatriculation.setText(student.getMatriculation());
        emptyJoined.setText(student.getJoined());
        emptyGraduationForecast.setText(student.getGraduationForecast());
        try {
            loadListView();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    @FXML
    void eventButtonExit(ActionEvent event) throws IOException {
        Management.changeScene("mainMenu.fxml", event);
    }

    public void loadListView() throws IOException, ClassNotFoundException {
        ObservableList<Activities> activitiesObservableList;
        Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());
        activitiesObservableList = FXCollections.observableArrayList(student.getListActivities());
        fieldListView.setItems(activitiesObservableList);
    }
}
