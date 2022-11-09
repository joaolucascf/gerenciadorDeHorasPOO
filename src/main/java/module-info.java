module poofinal.hoursmanager {
    requires javafx.controls;
    requires javafx.fxml;


    opens poofinal.hoursmanager to javafx.fxml;
    exports poofinal.hoursmanager;
}