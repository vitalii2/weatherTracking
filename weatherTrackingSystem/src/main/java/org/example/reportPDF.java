package org.example;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.Paragraph;
import java.io.FileNotFoundException;
import java.io.IOException;
public class reportPDF {
public static void createReport(String w){
    String report = "report.pdf";
    try(PdfWriter pw = new PdfWriter(report);) {
        PdfDocument pd = new PdfDocument(pw);
        pd.setDefaultPageSize(PageSize.A4);
        Document document = new Document(pd);
        document.add(new Paragraph(String.valueOf(w)));
        document.close();
    } catch (FileNotFoundException e) {
        System.out.println(e.getMessage());
    } catch (IOException e) {
        System.out.println(e.getMessage());
    }
}
}
