package poofinal.controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.Font.FontFamily;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.TextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.Mnemonic;
import javafx.stage.Stage;
import poofinal.application.ActivitiesCellFactor;
import poofinal.application.Management;
import poofinal.application.PDFGenerator;
import poofinal.entities.Activities;
import poofinal.entities.Student;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.ResourceBundle;

public class CtrlStudentActivitiesView implements Initializable {

    @FXML
    private Label emptyName;

    @FXML
    private ListView<Activities> fieldListViewActivities;

    @FXML
    void eventButtonExit(ActionEvent event) throws IOException {
        Management.changeScene("teacherPage.fxml", event);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());
        emptyName.setText(student.getName());
        try {
            student.loadFilesActivities();
            loadListView(student);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void loadListView(Student student) throws IOException, ClassNotFoundException {
        ObservableList<Activities> activitiesObservableList;
        activitiesObservableList = FXCollections.observableArrayList(student.getListActivities());
        fieldListViewActivities.setCellFactory(new ActivitiesCellFactor());
        fieldListViewActivities.setItems(activitiesObservableList);
        //System.out.println(activitiesObservableList.stream().sorted().toList());

    }

    public void eventButtonDone(ActionEvent actionEvent) throws IOException, DocumentException {
        //seta o estudante clicado como estudante atual
        Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());

        //importa todas as atividades declaradas por esse estudante nesse metodo
        List<Activities> acceptedActivities = student.getListActivities();

        //file path
        Path pdfPath = Path.of("out/reports/");

        PDFGenerator.pdfSaver(pdfPath, student.getName());

        File file = new File("src\\main\\resources\\files\\studentActivities\\" + student.getName() + "\\");
        if(file.exists() && file.isDirectory()){
            File listFiles[] = file.listFiles();
            for(int i = 0; i < listFiles.length; i++){
                listFiles[i].delete();
            }
        }
        file.deleteOnExit();
        Management.changeScene("successPDFGenerated.fxml", actionEvent);
    }
}

