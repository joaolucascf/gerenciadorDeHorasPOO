package poofinal.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import poofinal.application.Management;
import poofinal.application.StudentCellFactor;
import poofinal.entities.Student;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CtrlTeacherPage implements Initializable {

    @FXML
    private ListView<Student> listViewStudents;

   /* @FXML
    void eventButtonViewActivities(ActionEvent event) throws IOException, ClassNotFoundException {
        if(!listViewStudents.getSelectionModel().isEmpty()){
            Student student = listViewStudents.getSelectionModel().getSelectedItem();
            Management.setKeyMatriculation(student.getMatriculation());
            Management.changeScene("viewStudentActivities.fxml", event);
        }
    } */

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
        Management.changeScene("mainMenu.fxml", event);
    }

    @FXML
    void eventButtonExit(ActionEvent event) throws IOException {
        Management.changeScene("mainMenu.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    private void loadListView() throws IOException, ClassNotFoundException {
        ObservableList<Student> studentObservableList;
        Management.loadFiles();
        studentObservableList = FXCollections.observableArrayList(Management.getStudentBuffer().values());
        listViewStudents.setCellFactory(new StudentCellFactor());
        listViewStudents.setItems(studentObservableList);
    }
}
