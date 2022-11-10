package poofinal.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {

    static Stage stage = new Stage();
    @FXML
    private Button loginAluno;

    @FXML
    private TextField fieldCPF;

    @FXML
    private TextField fieldSIAPE;

    @FXML
    private Button loginServidor;

    @FXML
    private PasswordField fieldPasswordStudent;

    @FXML
    private PasswordField fieldPasswordTeacher;

    @FXML
    void eventLoginAluno(ActionEvent event) {

    }

    @FXML
    void eventLoginServidor(ActionEvent event) {

    }

    public void eventSignUp(MouseEvent mouseEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("signUpStudent.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setScene(scene);
        stage.show();
    }
}
