module poofinal.hoursmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires kernel;


    opens poofinal.application to javafx.fxml;
    exports poofinal.application;
}