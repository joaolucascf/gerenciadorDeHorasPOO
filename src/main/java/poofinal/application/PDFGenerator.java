package poofinal.application;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import poofinal.entities.Activities;
import poofinal.entities.Student;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.GeneralSecurityException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PDFGenerator {

    public static void pdfSaver(Path pdfPath, String pdfName) throws DocumentException, IOException, GeneralSecurityException {

        File pdfOut = new File(pdfPath + "/" + pdfName + "_" + LocalDate.now().format(DateTimeFormatter.ofPattern("dd_MM_yyyy")) + ".pdf");

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
        DriveUpdate.sendReport(pdfOut.getPath());
    }

    private static Document createReportPDF(Document pdfReport) throws DocumentException, IOException {
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
        Font title = new Font(Font.FontFamily.HELVETICA, 28, Font.BOLD);
        Paragraph titleLine = new Paragraph(String.format("Relatório de Admissão%nde Horas Complementares"), title);

        titleLine.setIndentationLeft(img.getScaledWidth()+pdfReport.leftMargin());
        titleLine.setSpacingBefore(img.getScaledHeight()*(-1));
        pdfReport.add(titleLine);
        titleLine.clear();

        //adiciona dados do aluno
        Paragraph writingLine = new Paragraph();
        newBlankLine(writingLine, 3);
        Font studentData = new Font(Font.FontFamily.HELVETICA, 12, Font.NORMAL);
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

    private static void newHoursTable(Document pdfReport, List<Activities> activitiesPrintable) throws DocumentException {
        String[] headerTitles = {"ATIVIDADE", "HORAS", "SITUAÇÃO", "JUSTIFICATIVA"};
        float[] columnsWidth = {200, 60, 120,200};
        int totalOfHoursApproved = 0;
        Font headerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD);

        //Instancia uma Tabela no PDF, alinha ao centro, e ajusta as dimensões das colunas
        PdfPTable hoursTable = new PdfPTable(4);
        hoursTable.setHorizontalAlignment(Element.ALIGN_CENTER);
        hoursTable.setTotalWidth(columnsWidth);

        //inicializa todas as string da header
        for(int i=0; i<headerTitles.length; i++) {
            PdfPCell column = new PdfPCell(new Phrase(headerTitles[i], headerFont));
            column.setHorizontalAlignment(Element.ALIGN_CENTER);
            hoursTable.addCell(column);
        }
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
            if(printingActivitie.getJustification()==null){
                hourCell = new PdfPCell(new Phrase("-"));
                hourCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            }else{
                hourCell = new PdfPCell(new Phrase(printingActivitie.getJustification()));
            }
            hoursTable.addCell(hourCell);
        }
        Font hourFont = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        PdfPCell totalCell = new PdfPCell(new Phrase("HORAS ADQUIRIDAS", hourFont));
        hoursTable.addCell(totalCell);
        totalCell = new PdfPCell(new Phrase(totalOfHoursApproved +" horas"));
        totalCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        hoursTable.addCell(totalCell);
        totalCell = new PdfPCell();
        hoursTable.addCell(totalCell);
        totalCell = new PdfPCell();
        hoursTable.addCell(totalCell);
        pdfReport.add(hoursTable);
    }

    private static void newBlankLine(Paragraph blankLine, int times) {
        for(int i=0; i < times; i++){
            blankLine.add(new Paragraph(" "));
        }
    }
}
