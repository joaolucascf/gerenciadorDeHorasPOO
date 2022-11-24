package poofinal.controllers;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import poofinal.application.ActivitiesCellFactor;
import poofinal.application.Management;
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
        Path pdfPath = Path.of("src/main/resources/files/reports/");
        File pdfOut = new File(pdfPath +"/"+student.getName()+".pdf");

        if(!Files.exists(pdfPath)) {
            Files.createDirectories(pdfPath);
        }

        //instancia documento e cria sua saida
        Document docPdf = new Document();
        PdfWriter.getInstance(docPdf, new FileOutputStream(pdfOut));

        //abre escrita no documento
        docPdf.open();
        docPdf = createReportPDF(docPdf);
        docPdf.close();

        /*File file = new File("src\\main\\resources\\files\\studentActivities\\" + student.getName() + "\\");
        if(file.exists() && file.isDirectory()){
            File listFiles[] = file.listFiles();
            for(int i = 0; i < listFiles.length; i++){
                listFiles[i].delete();
            }
        }
        file.deleteOnExit();*/
    }

    private Document createReportPDF(Document pdfReport) throws DocumentException, IOException {
        //seta o estudante clicado como estudante atual
        Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());
        //importa todas as atividades declaradas por esse estudante nesse metodo
        List<Activities> acceptedActivities = student.getListActivities();
        String isApproved;
        pdfReport.setPageSize(PageSize.A4);
        Image img = Image.getInstance("src/main/resources/files/img/UFPelShieldLogo.png");
        img.scaleToFit(150,150);
        System.out.println(String.valueOf(img.getAbsoluteX()));
        pdfReport.add(img);
        Paragraph linha = new Paragraph();
        pdfReport.add(new Paragraph(String.format("Aluno: %s", student.getName())));
        pdfReport.add(new Paragraph(String.format("Matrícula: %s", student.getMatriculation())));
        pdfReport.add(new Paragraph("ATIVIDADES"));
        for(int i=0; i<acceptedActivities.size(); i++){
            Activities insideAct = acceptedActivities.get(i);
            if(insideAct.getFlag()){
                isApproved = "APROVADO";
            }else {
                isApproved = "REPROVADO";
            }
            pdfReport.add(new Paragraph(insideAct.getDescription()+"_"+insideAct.getHours()+"_"+isApproved));
        }
        return pdfReport;
    }
}

