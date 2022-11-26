package poofinal.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import poofinal.application.FormatClass;
import poofinal.application.Management;
import poofinal.entities.Course;
import poofinal.entities.Student;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class CtrlSignUp implements Initializable {

    private final int MATRICULATION_MAX_LENGTH = 8;
    @FXML
    private ChoiceBox<String> choiceBoxCourse;
    @FXML
    private ChoiceBox<String> choiceBoxSemester;
    @FXML
    private ChoiceBox<String> choiceBoxYear;
    @FXML
    private Label emptyCourse;
    @FXML
    private Label emptyEmail;
    @FXML
    private Label emptyMatriculation;
    @FXML
    private Label emptyName;
    @FXML
    private Label emptySemester;
    @FXML
    private Label emptyYear;
    @FXML
    private TextField fieldEmail;
    @FXML
    private TextField fieldMatriculation;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldPassword;
    @FXML
    private Label invalidEmail;
    @FXML
    private Label invalidMatriculation;
    @FXML
    private Label invalidPassword;
    @FXML
    private Button buttonCancel;
    @FXML
    private Button buttonOK;
    private String year[] = {"2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022"};
    private String semester[] = {"1", "2"};

    public void initialize(URL url, ResourceBundle resourceBundle) {
        fieldName.setStyle("-fx-text-fill: white; -fx-background-color: #2F4F4F");
        fieldMatriculation.setStyle("-fx-text-fill: white; -fx-background-color: #2F4F4F");
        fieldEmail.setStyle("-fx-text-fill: white; -fx-background-color: #2F4F4F");
        fieldPassword.setStyle("-fx-text-fill: white; -fx-background-color: #2F4F4F");
        FormatClass.onlyAcceptNumbers(fieldMatriculation);
        FormatClass.addTextLimiter(fieldMatriculation, MATRICULATION_MAX_LENGTH);
        initializeChoiceBoxes();
    }

    @FXML
    void EventButtonCancel(ActionEvent event) throws IOException {
        Management.changeScene("mainMenu.fxml", event);
    }

    @FXML
    void eventButtonOK(ActionEvent event) throws IOException {
        if(validateEntries()) {
            readerInformation();
            Management.changeScene("successRegister.fxml", event);
        }
    }

    @FXML
    void mouseEnteredButtonCancel(MouseEvent event) {
        buttonCancel.setStyle("-fx-background-radius: 10; -fx-background-color: #004da5");
    }

    @FXML
    void mouseExitedButtonCancel(MouseEvent event) {
        buttonCancel.setStyle("-fx-background-radius: 10; -fx-background-color: #002651");
    }

    @FXML
    void mouseEnteredButtonOK(MouseEvent event) {
        buttonOK.setStyle("-fx-background-radius: 10; -fx-background-color: #004da5");
    }

    @FXML
    void mouseExitedButtonOK(MouseEvent event) {
        buttonOK.setStyle("-fx-background-radius: 10; -fx-background-color: #002651");
    }


    private void initializeChoiceBoxes(){
        choiceBoxCourse.setValue(" "); //o valor null gera exceção, então inicializei cada box com um espaço, para identificar a string "vazia"
        choiceBoxYear.setValue(" ");
        choiceBoxSemester.setValue(" ");
        for(Course course : Course.values()){
            choiceBoxCourse.getItems().add(course.getDescription());
        }
        choiceBoxYear.getItems().addAll(year);
        choiceBoxSemester.getItems().addAll(semester);
    }

    private boolean validateEntries(){
        refreshValidationFields();

        if (fieldName.getText().isEmpty()){
            fieldName.setStyle("-fx-text-fill: white; -fx-background-color: #2F4F4F");
            emptyName.setVisible(true);
            return false;
        }

        if(fieldEmail.getText().isEmpty()){
            fieldEmail.setStyle("-fx-text-fill: white; -fx-background-color: #2F4F4F");
            emptyEmail.setVisible(true);
            return false;
        }else
            if(!FormatClass.checkEmail(fieldEmail.getText())){
                fieldEmail.setStyle("-fx-text-fill: white; -fx-background-color: #2F4F4F");
                invalidEmail.setVisible(true);
            return false;
        }
        if(fieldMatriculation.getText().isEmpty()){
            fieldMatriculation.setStyle("-fx-text-fill: white; -fx-background-color: #2F4F4F");
            emptyMatriculation.setVisible(true);
            return false;
        }else if(fieldMatriculation.getLength()<8){
            fieldMatriculation.setStyle("-fx-text-fill: white; -fx-background-color: #2F4F4F");
            invalidMatriculation.setVisible(true);
            return false;
        }
        if(choiceBoxCourse.getValue().equals(" ")){
            choiceBoxCourse.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: red; -fx-background-color: white");
            emptyCourse.setVisible(true);
            return false;
        }
        if(choiceBoxYear.getValue().equals(" ")){
            choiceBoxYear.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: red; -fx-background-color: white");
            emptyYear.setVisible(true);
            return false;
        }
        if(choiceBoxSemester.getValue().equals(" ")){
            choiceBoxSemester.setStyle("-fx-background-radius: 10; -fx-border-radius: 10; -fx-border-color: red; -fx-background-color: white");
            emptySemester.setVisible(true);
            return false;
        }
        if(fieldPassword.getText().isEmpty()){
            fieldPassword.setStyle("-fx-text-fill: white; -fx-background-color: #2F4F4F");
            invalidPassword.setVisible(true);
            return false;
        }
        return true;
    }

    private void refreshValidationFields(){
        emptyName.setVisible(false);
        emptyEmail.setVisible(false);
        invalidEmail.setVisible(false);
        emptyMatriculation.setVisible(false);
        invalidMatriculation.setVisible(false);
    }

    private void readerInformation() throws IOException {
        String password = fieldPassword.getText();
        String name = fieldName.getText();
        String email = fieldEmail.getText();
        String matriculation = fieldMatriculation.getText();
        String course = choiceBoxCourse.getValue();
        int semester = Integer.parseInt(choiceBoxSemester.getValue());
        int year = Integer.parseInt(choiceBoxYear.getValue());
        LocalDate joined = LocalDate.of(year, semester, 01);
        LocalDate graduationForecast;
        Course courseFinal;
        if(course.equals("Engenharia de Computação")){
            graduationForecast = LocalDate.of((year + 5), semester, 01);
            courseFinal = Course.ENGENHARIA;
        }
        else{
            graduationForecast = LocalDate.of((year + 4), semester, 01);
            courseFinal = Course.CIENCIA;
        }
        Management.addStudent(new Student(password, matriculation, joined, graduationForecast, name, email, courseFinal));
    }
}




