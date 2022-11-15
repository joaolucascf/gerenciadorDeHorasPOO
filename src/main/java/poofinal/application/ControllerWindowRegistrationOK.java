package poofinal.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class ControllerWindowRegistrationOK {

    @FXML
    private Button buttonOK;

    private Stage stage;
    private Scene scene;
    @FXML
    void eventButtonOK(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(FXMLLoader.load(getClass().getResource("mainMenu.fxml")));
        stage.setScene(scene);
        stage.show();
    }

}
