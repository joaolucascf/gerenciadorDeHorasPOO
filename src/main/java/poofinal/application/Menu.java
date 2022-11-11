package poofinal.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Collection;
import java.util.ResourceBundle;

public class Menu{

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
    private Stage stage;
    private Scene scene;
    @FXML
    void eventLoginAluno(ActionEvent event) throws IOException {
        //Validar, caso o e-mail e a senha não existam nos cadastros,
        //então ele retorna a pagina de login com o border vermelho.
        //System.out.println("Marquinhos bom de bola");
        this.stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        this.scene = new Scene(FXMLLoader.load(getClass().getResource("StudentPage.fxml")));
        this.stage.setScene(scene);
        this.stage.show();
    }
    @FXML
    void eventLoginServidor(ActionEvent event) {

    }
    public void eventSignUp(MouseEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(FXMLLoader.load(getClass().getResource("signUpStudent.fxml")));
        stage.setScene(scene);
        stage.show();
    }

}
