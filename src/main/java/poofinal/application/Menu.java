package poofinal.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Menu implements Initializable{
    @FXML
    private Button loginAluno;
    @FXML
    private TextField fieldMatriculation;
    @FXML
    private TextField fieldSIAPE;
    @FXML
    private Button loginServidor;
    @FXML
    private PasswordField fieldPasswordStudent;
    @FXML
    private PasswordField fieldPasswordTeacher;
    private Stage stage;
    private Scene scene;

    public void initialize(URL url, ResourceBundle resourceBundle) {
        FormatClass.onlyAcceptNumbers(fieldMatriculation);
        FormatClass.addTextLimiter(fieldMatriculation, 8);
    }

    @FXML
    void eventLoginAluno(ActionEvent event) throws IOException, ClassNotFoundException {
        if(Management.checkLoginStudent(fieldMatriculation.getText(), fieldPasswordStudent.getText()) == true){
            String nameFXML = "StudentPage.fxml";
            Management.changeScene(nameFXML, event);
        }
        else{
            System.out.println("Erro");
        }
    }
    @FXML
    void eventLoginServidor(ActionEvent event) {

    }
    public void eventSignUp(MouseEvent event) throws IOException {
        String nameFXML = "signUpStudent.fxml";
        Management.changeScene(nameFXML, event);
    }
}
