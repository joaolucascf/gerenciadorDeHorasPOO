package poofinal.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import poofinal.application.Management;
import poofinal.application.PageStudentCellFactor;
import poofinal.entities.Activities;
import poofinal.entities.Student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CtrlStudentPage implements Initializable{

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
    void eventButtonActivities(ActionEvent event) throws IOException {
        Management.changeScene("addActivities.fxml", event);
    }

    @FXML
    void eventButtonExit(ActionEvent event) throws IOException {
        Management.changeScene("mainMenu.fxml", event);
    }

    @FXML
    void mouseEnteredButtonAddActivities(MouseEvent event) {
        buttonActivities.setStyle("-fx-background-radius: 10; -fx-background-color: #004da5");
    }

    @FXML
    void mouseExitedButtonAddActivities(MouseEvent event) {
        buttonActivities.setStyle("-fx-background-radius: 10; -fx-background-color: #002651");
    }

    @FXML
    void mouseEnteredButtonExit(MouseEvent event) {
        buttonExit.setStyle("-fx-background-radius: 10; -fx-background-color: #004da5");
    }

    @FXML
    void mouseExitedButtonExit(MouseEvent event) {
        buttonExit.setStyle("-fx-background-radius: 10; -fx-background-color: #002651");
    }

    public void loadListView() throws IOException, ClassNotFoundException {
        ObservableList<Activities> activitiesObservableList;
        Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());
        activitiesObservableList = FXCollections.observableArrayList(student.getListActivities());
        fieldListView.setCellFactory(new PageStudentCellFactor());
        fieldListView.setItems(activitiesObservableList);
    }
}
