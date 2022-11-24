package poofinal.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import poofinal.application.FormatClass;
import poofinal.application.Management;
import poofinal.entities.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CtrlMainMenu implements Initializable{
    public Label invalidMatriculation;
    public Label invalidPassword;
    public Label wrongPassword;
    @FXML
    private Button buttonLoginStudent;

    @FXML
    private Button buttonLoginTeacher;

    @FXML
    private TextField fieldMatriculation;

    @FXML
    private PasswordField fieldPasswordStudent;
    @FXML
    private Text textSignUpStudent;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        FormatClass.onlyAcceptNumbers(fieldMatriculation);
        FormatClass.addTextLimiter(fieldMatriculation, 8);
    }

    @FXML
    void eventLoginStudent(ActionEvent event) throws IOException, ClassNotFoundException {
        if(validateLoginEntries()) {
            if (Management.checkLoginStudent(fieldMatriculation.getText(), fieldPasswordStudent.getText())) {
                Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());
                Management.loadTableActivities();
                student.loadFilesActivities();
                Management.changeScene("studentPage.fxml", event);
            } else {
                fieldPasswordStudent.setStyle("-fx-border-color:red");
                wrongPassword.setVisible(true);
            }
        }
    }

    private boolean validateLoginEntries() {
        refreshValidators();
        if(fieldMatriculation.getText().isEmpty() || fieldMatriculation.getText().length()<8){
            fieldMatriculation.setStyle("-fx-border-color:red");
            invalidMatriculation.setVisible(true);
            return false;
        }
        if(fieldPasswordStudent.getText().isEmpty()){
            fieldPasswordStudent.setStyle("-fx-border-color:red");
            invalidPassword.setVisible(true);
            return false;
        }
        return true;
    }

    private void refreshValidators() {
        fieldMatriculation.setStyle(null);
        fieldPasswordStudent.setStyle(null);
        invalidMatriculation.setVisible(false);
        invalidPassword.setVisible(false);
        wrongPassword.setVisible(false);
    }

    @FXML
    void eventLoginTeacher(ActionEvent event) {
        try {
            Management.changeScene("teacherPage.fxml", event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void eventSignUpStudent(MouseEvent event) throws IOException {
        Management.changeScene("signUpStudent.fxml", event);
    }

    @FXML
    void eventMouseEnteredStudent(MouseEvent event) {
        textSignUpStudent.setFill(Color.BLUE);
        textSignUpStudent.setUnderline(true);
    }

    @FXML
    void eventMouseExitedStudent(MouseEvent event) {
        textSignUpStudent.setFill(Color.BLACK);
        textSignUpStudent.setUnderline(false);
    }
}
