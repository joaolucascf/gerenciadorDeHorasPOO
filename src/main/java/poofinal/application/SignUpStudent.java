package poofinal.application;

import javafx.beans.property.ReadOnlyIntegerProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import poofinal.entities.Course;
import poofinal.entities.Student;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Pattern;

public class SignUpStudent implements Initializable {
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
    @FXML
    private TextField fieldCPF;
    private Stage stage;
    private Scene scene;
    private String year[] = {"2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022"};
    private String semester[] = {"1", "2"};

    public void initialize(URL url, ResourceBundle resourceBundle) { //initialize sempre executa quando a cena entra na tela
        fieldCPF.setTextFormatter(new TextFormatter<String>(new formatCPF().justNumbers)); //formata o fieldCPF para aceitar apenas números
        for(Course course : Course.values()){
            choiceBoxCourse.getItems().add(course.getDescription());
        }
        choiceBoxYear.getItems().addAll(year);
        choiceBoxSemester.getItems().addAll(semester);
    }

    @FXML
    void EventButtonCancel(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(FXMLLoader.load(getClass().getResource("mainMenu.fxml")));
        stage.setScene(scene);
        stage.show();
    }
    @FXML
    void eventButtonOK(ActionEvent event) {
        readerInformation();
    }
    public void readerInformation(){
        String cpf = fieldCPF.getText();
        String password = fieldPassword.getText();
        String name = fieldName.getText();
        String email = fieldEmail.getText();
        String matriculation = fieldMatriculation.getText();
        String course = choiceBoxCourse.getValue();
        int semester = Integer.parseInt(choiceBoxSemester.getValue());
        int year = Integer.parseInt(choiceBoxYear.getValue());
        if(semester == 2){
            semester += 5;
        }
        LocalDate joined = LocalDate.of(year, semester, 01);
        LocalDate gradutionForecast;
        Course courseFinal;
        if(course.equals("Engenharia de Computação")){
            gradutionForecast = LocalDate.of((year + 5), semester, 01);
            courseFinal = Course.ENGENHARIA;
        }
        else{
            gradutionForecast = LocalDate.of((year + 4), semester, 01);
            courseFinal = Course.CIENCIA;
        }
        Management.addStudent(new Student(password, cpf, matriculation, joined, gradutionForecast, name, email, courseFinal));
        Management.printStudent();
    }
}




