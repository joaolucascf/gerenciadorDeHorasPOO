package poofinal.application;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import poofinal.entities.Activities;

public class PageStudentCellFactor implements Callback<ListView<Activities>, ListCell<Activities>> {
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
                    HBox linha = new HBox();
                    Label label = new Label("\t" + activities.getDescription() + "\n\t" + activities.getHours() + " Horas");
                    linha.getChildren().addAll(label);
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
