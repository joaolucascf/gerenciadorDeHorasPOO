package poofinal.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import poofinal.application.Management;

import java.io.IOException;

public class CtrlPDFGenerated {
    public void eventButtonOK(ActionEvent actionEvent) throws IOException {
        Management.changeScene("teacherPage.fxml", actionEvent);
    }
}
