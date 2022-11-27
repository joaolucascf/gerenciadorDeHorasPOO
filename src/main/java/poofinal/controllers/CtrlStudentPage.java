package poofinal.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import poofinal.application.Management;
import poofinal.application.PageStudentCellFactor;
import poofinal.entities.Activities;
import poofinal.entities.Student;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CtrlStudentPage implements Initializable{

    public Text textDateJoined;
    public Text textForecast;
    public Text textCourse;
    public Text textMatriculation;
    @FXML
    private Button buttonActivities;
    @FXML
    private Button buttonExit;
    @FXML
    private Label labelName;
    @FXML
    private ListView<Activities> fieldListView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());
        labelName.setText(student.getName());
        textCourse.setText(textCourse.getText()+ " " +student.getCourse().getDescription());
        textMatriculation.setText(textMatriculation.getText()+ " " +student.getMatriculation());
        textDateJoined.setText(textDateJoined.getText()+ " " +student.getJoined());
        textForecast.setText(textForecast.getText()+ " " +student.getGraduationForecast());
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
