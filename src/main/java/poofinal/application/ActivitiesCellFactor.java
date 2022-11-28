package poofinal.application;

import javafx.application.HostServices;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import java.awt.Desktop;
import poofinal.controllers.CtrlStudentActivitiesView;
import poofinal.entities.Activities;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class ActivitiesCellFactor implements Callback<ListView<Activities>, ListCell<Activities>>{
    @Override
    public ListCell<Activities> call(ListView<Activities> activitiesListView) {
        return new ListCell<>() {
            @Override
            public void updateItem(Activities activities, boolean empty) {
                super.updateItem(activities, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else if(activities != null){
                    CheckBox checkBox = new CheckBox();
                    TextField textField = new TextField();
                    textField.setVisible(true);
                    checkBox.setText("OK ");
                    HBox linha = new HBox();
                    Hyperlink link = new Hyperlink("Link do Comprovante");
                    textField.setOnKeyReleased((event)->{
                        String string = textField.getText();
                        activities.setJustification(string);
                    });
                    checkBox.setOnAction((event)->{
                        textField.setVisible(!checkBox.isSelected());
                        activities.setFlag(checkBox.isSelected());
                        textField.setText(null);
                        activities.setJustification(null);
                    });
                    link.setOnMouseClicked((event)->{
                        try {
                            openWebpage(new URL(activities.getLink()).toURI());
                        } catch (URISyntaxException e) {
                            link.setText("URL Inválido!");
                            link.setStyle("-fx-text-fill: red");
                        } catch (MalformedURLException e) {
                            link.setText("URL Inválido!");
                            link.setStyle("-fx-text-fill: red");
                        }
                    });
                    Label label = new Label("\t" + activities.getDescription() + "\n\t" + activities.getHours());
                    linha.getChildren().addAll(checkBox, textField, link, label);
                    setText(null);
                    setGraphic(linha);
                } else{
                    setText(null);
                    setGraphic(null);
                }
            }
        };
    }

    public static boolean openWebpage(URI uri) {
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
            try {
                desktop.browse(uri);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}