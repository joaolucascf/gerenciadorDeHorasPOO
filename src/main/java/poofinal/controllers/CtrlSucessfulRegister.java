package poofinal.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import poofinal.application.Management;

import java.io.IOException;

public class CtrlSucessfulRegister {

    @FXML
    private Button buttonOK;

    @FXML
    void eventButtonOK(ActionEvent event) throws IOException {
        Management.changeScene("mainMenu.fxml", event);
    }
}
