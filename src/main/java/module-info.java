module poofinal.hoursmanager {
    requires javafx.controls;
    requires javafx.fxml;
    requires itextpdf;
    requires google.api.client;
    requires com.google.gson;
    requires com.google.api.client;
    requires com.google.api.client.extensions.jetty.auth;
    requires com.google.api.client.extensions.java6.auth;
    requires com.google.api.client.auth;
    requires com.google.api.services.drive;
    requires com.google.api.client.json.gson;
    requires jdk.httpserver;
    requires java.desktop;

    opens poofinal.application to javafx.fxml;
    exports poofinal.application;
    exports poofinal.controllers;
    opens poofinal.controllers to javafx.fxml;
}