package poofinal.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

import java.io.IOException;

public class ControllerWindowRegistrationOK {

    @FXML
    private Button buttonOK;

    @FXML
    void eventButtonOK(ActionEvent event) throws IOException {
        Management.changeScene("mainMenu.fxml", event);
    }

}
