package poofinal.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import poofinal.entities.Course;
import poofinal.entities.Student;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class ControllerSignUpStudent implements Initializable {

    private final int MATRICULATION_MAX_LENGTH = 8;

    @FXML
    public Label emptyName;
    public Label invalidEmail;
    public Label emptyEmail;
    public Label invalidMatriculation;
    public Label emptyMatriculation;

    @FXML
    private TextField fieldEmail;
    @FXML
    private TextField fieldMatriculation;
    @FXML
    private ChoiceBox<String> choiceBoxCourse;
    @FXML
    private ChoiceBox<String> choiceBoxSemester;
    @FXML
    private ChoiceBox<String> choiceBoxYear;
    @FXML
    private TextField fieldName;
    @FXML
    private TextField fieldPassword;

    private Stage stage;
    private Scene scene;

    private String year[] = {"2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022"};
    private String semester[] = {"1", "2"};

    public void initialize(URL url, ResourceBundle resourceBundle) {
        FormatClass.onlyAcceptNumbers(fieldMatriculation);
        FormatClass.addTextLimiter(fieldMatriculation, MATRICULATION_MAX_LENGTH);
        initializeChoiceBoxes();
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

    @FXML
    void EventButtonCancel(ActionEvent event) throws IOException {
        Management.changeScene("mainMenu.fxml", event);
    }
    @FXML
    void eventButtonOK(ActionEvent event) throws IOException {
        if(validateEntries()) {
            readerInformation();
            Management.changeScene("windowRegistrationOK.fxml", event);
        }
    }

    private boolean validateEntries(){
        refreshValidationFields();
        fieldName.setStyle(null);

        if (fieldName.getText().isEmpty()){
            fieldName.setStyle("-fx-border-color:red");
            emptyName.setVisible(true);
            return false;
        }

        if(fieldEmail.getText().isEmpty()){
            fieldEmail.setStyle("-fx-border-color:red");
            emptyEmail.setVisible(true);
            return false;
        }else
            if(!FormatClass.checkEmail(fieldEmail.getText())){
            fieldEmail.setStyle("-fx-border-color:red");
            invalidEmail.setVisible(true);
            return false;
        }
        if(fieldMatriculation.getText().isEmpty()){
            fieldMatriculation.setStyle("-fx-border-color:red");
            emptyMatriculation.setVisible(true);
            return false;
        }else if(fieldMatriculation.getLength()<8){
            fieldMatriculation.setStyle("-fx-border-color:red");
            invalidMatriculation.setVisible(true);
            return false;
        }
        if(choiceBoxCourse.getValue().equals(" ")){
            choiceBoxCourse.setStyle("-fx-border-color:red");
            return false;
        }
        if(choiceBoxYear.getValue().equals(" ")){
            choiceBoxYear.setStyle("-fx-border-color:red");
            return false;
        }
        if(choiceBoxSemester.getValue().equals(" ")){
            choiceBoxSemester.setStyle("-fx-border-color:red");
            return false;
        }
        if(fieldPassword.getText().isEmpty()){
            fieldPassword.setStyle("-fx-border-color:red");
            return false;
        }
        return true;
    }

    private void refreshValidationFields(){
        fieldName.setStyle(null);
        fieldEmail.setStyle(null);
        fieldMatriculation.setStyle(null);
        fieldPassword.setStyle(null);
        choiceBoxCourse.setStyle(null);
        choiceBoxYear.setStyle(null);
        choiceBoxSemester.setStyle(null);
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




