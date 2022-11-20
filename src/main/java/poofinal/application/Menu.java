package poofinal.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import poofinal.entities.Student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu implements Initializable{
    @FXML
    private Button buttonLoginStudent;

    @FXML
    private Button buttonLoginTeacher;

    @FXML
    private TextField fieldMatriculation;

    @FXML
    private PasswordField fieldPasswordStudent;

    @FXML
    private PasswordField fieldPasswordTeacher;

    @FXML
    private TextField fieldSIAPE;

    @FXML
    private Text textSignUpStudent;

    @FXML
    private Text textSignUpTeacher;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        FormatClass.onlyAcceptNumbers(fieldMatriculation);
        FormatClass.addTextLimiter(fieldMatriculation, 8);
    }

    @FXML
    void eventLoginStudent(ActionEvent event) throws IOException, ClassNotFoundException {
        Management.loadFiles();// Estamos usando uma string estatica. Precisamos fazer generica
        if (Management.checkLoginStudent(fieldMatriculation.getText(), fieldPasswordStudent.getText())) {
            Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());
            student.loadFilesActivities();
            Management.changeScene("StudentPage.fxml", event);
        } else {
            System.out.println("Erro");
        }
    }
    @FXML
    void eventLoginTeacher(ActionEvent event) {

    }
    @FXML
    void eventSignUpTeacher(MouseEvent event) throws IOException {
        //Management.changeScene("", event);
    }

    @FXML
    void eventMouseEnteredTeacher(MouseEvent event) {
        textSignUpTeacher.setFill(Color.BLUE);
        textSignUpTeacher.setUnderline(true);
    }

    @FXML
    void eventMouseExitedTeacher(MouseEvent event) {
        textSignUpTeacher.setFill(Color.BLACK);
        textSignUpTeacher.setUnderline(false);
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
