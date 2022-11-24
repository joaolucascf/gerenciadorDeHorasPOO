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
    //Daqui pra baixo criador de PDF
    private Document createReportPDF(Document pdfReport) throws DocumentException, IOException {
        Student student = Management.getStudentBuffer().get(Management.getKeyMatriculation());
        List<Activities> acceptedActivities = student.getListActivities();

        String isApproved;

        //inicializa o tamanho do PDF e coloca um titulo no arquivo
        pdfReport.setPageSize(PageSize.A4);
        pdfReport.addTitle("Relatório de Admissão de Horas Complementares");

        //adiciona o logo da ufpel
        Image img = Image.getInstance("src/main/resources/files/img/UFPelShieldLogo.png");
        img.scaleToFit(100,100);
        pdfReport.add(img);

        //adiciona o titulo no PDF
        Font title = new Font(FontFamily.HELVETICA, 28, Font.BOLD);
        Paragraph titleLine = new Paragraph(String.format("Relatório de Admissão%nde Horas Complementares"), title);

        titleLine.setIndentationLeft(img.getScaledWidth()+pdfReport.leftMargin());
        titleLine.setSpacingBefore(img.getScaledHeight()*(-1));
        pdfReport.add(titleLine);
        titleLine.clear();

        //adiciona dados do aluno
        Paragraph writingLine = new Paragraph();
        newBlankLine(writingLine, 3);
        Font studentData = new Font(FontFamily.HELVETICA, 12, Font.NORMAL);
        writingLine.add(new Paragraph(String.format("Aluno:     %s%n", student.getName()), studentData));
        writingLine.add(new Paragraph(String.format("Matrícula: %s%n", student.getMatriculation()), studentData));
        writingLine.add(new Paragraph(String.format("E-mail:    %s%n", student.getEmail()), studentData));
        writingLine.add(new Paragraph(String.format("Curso:     %s%n", student.getCourse().getDescription()), studentData));
        newBlankLine(writingLine,3);
        pdfReport.add(writingLine);
        writingLine.clear();

        //adiciona a tabela
        newHoursTable(pdfReport, acceptedActivities);

        return pdfReport;
    }

    private void newHoursTable(Document pdfReport, List<Activities> activitiesPrintable) throws DocumentException {
        float[] columnsWidth = {200, 50, 80};
        int totalOfHoursApproved = 0;
        PdfPTable hoursTable = new PdfPTable(3);
        hoursTable.setHorizontalAlignment(Element.ALIGN_CENTER);
        hoursTable.setTotalWidth(columnsWidth);
        PdfPCell column = new PdfPCell(new Phrase("ATIVIDADE"));
        column.setHorizontalAlignment(Element.ALIGN_CENTER);
        hoursTable.addCell(column);
        column = new PdfPCell(new Phrase("HORAS"));
        column.setHorizontalAlignment(Element.ALIGN_CENTER);
        hoursTable.addCell(column);
        column = new PdfPCell(new Phrase("SITUAÇÃO"));
        column.setHorizontalAlignment(Element.ALIGN_CENTER);
        hoursTable.addCell(column);
        hoursTable.setHeaderRows(1);
        for(int i = 0; i<activitiesPrintable.size(); i++){
            Activities printingActivitie = activitiesPrintable.get(i);
            PdfPCell hourCell = new PdfPCell(new Phrase(printingActivitie.getDescription()));
            hourCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            hoursTable.addCell(hourCell);
            hourCell = new PdfPCell(new Phrase(printingActivitie.getHours()+" horas"));
            hourCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hoursTable.addCell(hourCell);
            if(printingActivitie.getFlag()){
                totalOfHoursApproved += Integer.parseInt(printingActivitie.getHours());
                hourCell = new PdfPCell(new Phrase("APROVADO"));
            }else{
                hourCell = new PdfPCell(new Phrase("REPROVADO"));
            }
            hourCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            hoursTable.addCell(hourCell);
        }
        PdfPCell totalCell = new PdfPCell(new Phrase("TOTAL DE HORAS ADQUIRIDAS"));
        hoursTable.addCell(totalCell);
        totalCell = new PdfPCell(new Phrase(totalOfHoursApproved +" horas"));
        totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hoursTable.addCell(totalCell);
        totalCell = new PdfPCell();
        hoursTable.addCell(totalCell);
        pdfReport.add(hoursTable);
    }

    private void newBlankLine(Paragraph blankLine, int times) {
        for(int i=0; i < times; i++){
            blankLine.add(new Paragraph(" "));
        }
    }
}

