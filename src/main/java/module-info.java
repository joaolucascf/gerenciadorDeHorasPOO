module poofinal.hoursmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires itextpdf;


    opens poofinal.application to javafx.fxml;
    exports poofinal.application;
    exports poofinal.controllers;
    opens poofinal.controllers to javafx.fxml;
}