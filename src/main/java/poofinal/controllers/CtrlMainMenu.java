package poofinal.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import poofinal.application.FormatClass;
import poofinal.application.Management;
import poofinal.entities.Student;
import poofinal.entities.Teacher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CtrlMainMenu implements Initializable{
    @FXML
    public Label invalidMatriculation;
    @FXML
    private Button buttonLoginStudent;
    @FXML
    public Label invalidPassword;
    @FXML
    public Label wrongPassword;
    @FXML
    private TextField fieldMatriculation;
    @FXML
    private PasswordField fieldPasswordStudent;
    @FXML
    private Text textSignUpStudent;
    @FXML
    private Button buttonLoginTeacher;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fieldMatriculation.setStyle("-fx-text-fill: white; -fx-background-color: #008080");
        fieldPasswordStudent.setStyle("-fx-text-fill: white; -fx-background-color: #008080");
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
                Management.changeScene("StudentPage.fxml", event);
            } else {
                fieldPasswordStudent.setStyle("-fx-text-fill: white; -fx-background-color: #008080");
                wrongPassword.setVisible(true);
            }
        }
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
    void mouseEnteredButtonLoginTeacher(MouseEvent event) {
        buttonLoginTeacher.setStyle("-fx-background-color: #004da5");
    }

    @FXML
    void mouseExitedButtonLoginTeacher(MouseEvent event) {
        buttonLoginTeacher.setStyle("-fx-background-color: #002651");
    }

    @FXML
    void mouseEnteredSignUp(MouseEvent event) {
        textSignUpStudent.setFill(Color.BLUE);
        textSignUpStudent.setUnderline(true);
    }

    @FXML
    void mouseExitedSignUp(MouseEvent event) {
        textSignUpStudent.setFill(Color.WHITE);
        textSignUpStudent.setUnderline(false);
    }

    @FXML
    void mouseEnteredButtonLogin(MouseEvent event){
        buttonLoginStudent.setStyle("-fx-background-color: #004da5");
    }

    @FXML
    void mouseExitedButtonLogin(MouseEvent event){
        buttonLoginStudent.setStyle("-fx-background-color: #002651");
    }

    private boolean validateLoginEntries() {
        refreshValidators();
        if(fieldMatriculation.getText().isEmpty() || fieldMatriculation.getText().length()<8){
            fieldMatriculation.setStyle("-fx-text-fill: white; -fx-background-color:  #008080");
            invalidMatriculation.setVisible(true);
            return false;
        }
        if(fieldPasswordStudent.getText().isEmpty()){
            fieldPasswordStudent.setStyle("-fx-text-fill: white; -fx-background-color:  #008080");
            invalidPassword.setVisible(true);
            return false;
        }
        return true;
    }

    private void refreshValidators() {
        invalidMatriculation.setVisible(false);
        invalidPassword.setVisible(false);
        wrongPassword.setVisible(false);
    }
}
