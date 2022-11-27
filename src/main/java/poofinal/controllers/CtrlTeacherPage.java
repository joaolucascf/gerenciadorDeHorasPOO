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
import poofinal.application.Management;
import poofinal.application.StudentCellFactor;
import poofinal.entities.Student;
import poofinal.entities.Teacher;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CtrlTeacherPage implements Initializable {

    public Label SIAPEfield;
    @FXML
    private ListView<Student> listViewStudents;
    @FXML
    private Label teacherName;
    @FXML
    private Button buttonEdit;
    @FXML
    private Button buttonExit;
    @FXML
    private Button buttonView;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Teacher teacher = new Teacher("Ulisses Brisolara Corrêa", "999707070");
        teacherName.setText(teacher.getName());
        SIAPEfield.setText(teacher.getSiape());
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
    void eventMouseClicked(MouseEvent event) throws IOException {
        if(event.getClickCount() == 2){
            if(!listViewStudents.getSelectionModel().isEmpty()){
                Student student = listViewStudents.getSelectionModel().getSelectedItem();
                Management.setKeyMatriculation(student.getMatriculation());
                Management.changeScene("viewStudentActivities.fxml", event);
            }
        }
    }

    @FXML
    void eventButtonEditTable(ActionEvent event) throws IOException {
        Management.loadTableActivities();
        Management.changeScene("tableEditor.fxml", event);
    }

    @FXML
    void eventButtonViewReceipts(ActionEvent event) {
        try {
            Runtime.getRuntime().exec("explorer out\\reports");
        } catch (IOException e) {
            System.out.println("Caminho não existe!");
        }
    }

    @FXML
    void eventButtonExit(ActionEvent event) throws IOException {
        Management.changeScene("mainMenu.fxml", event);
    }

    @FXML
    void mouseEnteredButtonEdit(MouseEvent event) {
        buttonEdit.setStyle("-fx-background-radius: 10; -fx-background-color: #004da5");
    }

    @FXML
    void mouseExitedButtonEdit(MouseEvent event) {
        buttonEdit.setStyle("-fx-background-radius: 10; -fx-background-color: #002651");
    }

    @FXML
    void mouseEnteredButtonExit(MouseEvent event) {
        buttonExit.setStyle("-fx-background-radius: 10; -fx-background-color: #004da5");
    }

    @FXML
    void mouseExitedButtonExit(MouseEvent event) {
        buttonExit.setStyle("-fx-background-radius: 10; -fx-background-color: #002651");

    }

    @FXML
    void mouseEnteredButtonView(MouseEvent event) {
        buttonView.setStyle("-fx-background-radius: 10; -fx-background-color: #004da5");
    }

    @FXML
    void mouseExitedButtonView(MouseEvent event) {
        buttonView.setStyle("-fx-background-radius: 10; -fx-background-color: #002651");

    }

    private void loadListView() throws IOException, ClassNotFoundException {
        ObservableList<Student> studentObservableList;
        Management.loadFiles();
        studentObservableList = FXCollections.observableArrayList(Management.getStudentBuffer().values());
        listViewStudents.setCellFactory(new StudentCellFactor());
        listViewStudents.setItems(studentObservableList);
    }
}
