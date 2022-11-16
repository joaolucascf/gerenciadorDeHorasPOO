package poofinal.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        Management.changeScene("mainMenu.fxml", event);
    }

}
