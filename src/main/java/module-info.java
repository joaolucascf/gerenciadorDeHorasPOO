module poofinal.hoursmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens poofinal.application to javafx.fxml;
    exports poofinal.application;
}