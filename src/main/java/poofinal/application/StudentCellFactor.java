package poofinal.application;

import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.util.Callback;
import poofinal.entities.Student;

public class StudentCellFactor implements Callback<ListView<Student>, ListCell<Student>> {
    @Override
    public ListCell<Student> call(ListView<Student> listViewStudents) {
        return new ListCell<>() {
            @Override
            public void updateItem(Student student, boolean empty) {
                super.updateItem(student, empty);
                if (empty) {
                    setText(null);
                    setGraphic(null);
                } else if(student != null){
                    HBox linha = new HBox();
                    Label label = new Label(student.getName() + "     " + student.getMatriculation() + "\t\t\t\n" + student.getCourse().getDescription());
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