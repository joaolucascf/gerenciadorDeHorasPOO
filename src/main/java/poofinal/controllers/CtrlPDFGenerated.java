package poofinal.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import poofinal.application.Management;

import java.io.IOException;

public class CtrlPDFGenerated {

    @FXML
    private Button buttonOK;

    @FXML
    public void eventButtonOK(ActionEvent actionEvent) throws IOException {
        Management.changeScene("teacherPage.fxml", actionEvent);
    }

    @FXML
    void mouseEnteredButtonOK(MouseEvent event) {
        buttonOK.setStyle("-fx-background-radius: 10; -fx-background-color: #004da5");
    }

    @FXML
    void mouseExitedButtonOK(MouseEvent event) {
        buttonOK.setStyle("-fx-background-radius: 10; -fx-background-color: #002651");

    }
}
