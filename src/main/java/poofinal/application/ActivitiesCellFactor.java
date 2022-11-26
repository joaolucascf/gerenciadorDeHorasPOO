package poofinal.application;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import poofinal.controllers.CtrlStudentActivitiesView;
import poofinal.entities.Activities;

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

                    Label label = new Label("\t" + activities.getDescription() + "\n\t" + activities.getHours());
                    linha.getChildren().addAll(checkBox, textField, label);
                    setText(null);
                    setGraphic(linha);
                } else{
                    setText(null);
                    setGraphic(null);
                }
            }
        };
    }
}