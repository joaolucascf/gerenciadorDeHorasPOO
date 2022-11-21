package poofinal.application;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import poofinal.entities.Activities;
import poofinal.entities.Student;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewStudentActivities implements Initializable {

    @FXML
    private Label emptyName;

    @FXML
    private ListView<Activities> fieldListViewActivities;

    @FXML
    void eventButtonExit(ActionEvent event) throws IOException {
        Management.changeScene("teacherPage.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());
        emptyName.setText(student.getName());
        try {
            student.loadFilesActivities();
            loadListView(student);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadListView(Student student) throws IOException, ClassNotFoundException {
        ObservableList<Activities> activitiesObservableList;
        activitiesObservableList = FXCollections.observableArrayList(student.getListActivities());
        if(!activitiesObservableList.isEmpty()) {
            fieldListViewActivities.setItems(activitiesObservableList);
        }
        else{
            System.out.println("Este aluno não possuí atividades!");
        }
    }

}
