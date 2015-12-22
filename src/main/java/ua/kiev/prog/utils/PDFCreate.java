package ua.kiev.prog.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import ua.kiev.prog.entity.CurrentPayments;
import ua.kiev.prog.entity.UserEntity;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class PDFCreate {

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    final String FONT = "c:/windows/fonts/arial.ttf";
    static int COUNT = 1;


    public void createPDF(UserEntity userEntity) {
        try {
            Document document = new Document(PageSize.LETTER);
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("D:\\Test.pdf"));
            BaseFont baseFont = BaseFont.createFont(FONT, BaseFont.IDENTITY_H, true);
            Font font = new Font(baseFont);
            document.open();
            generateHeader(document, font, userEntity);
            generateTable(document, font, userEntity);
            generateClosing(document, font);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateHeader(Document document, Font font, UserEntity userEntity) throws DocumentException {
        Paragraph paragraph0 = new Paragraph();
        Paragraph paragraph1 = new Paragraph();
        Paragraph paragraph2 = new Paragraph();
        Paragraph paragraph3 = new Paragraph();
        Paragraph paragraph4 = new Paragraph();

        paragraph4.add(new Paragraph("Квитанция", font));
        paragraph3.add(new Paragraph("Адрес: Украина, город " + userEntity.getBuildsEntity().getCity() + ", "
                + userEntity.getBuildsEntity().getStreet() + ", " + userEntity.getBuildsEntity().getBuildNum()
                + "/" + userEntity.getUserInfo().getFlatsEntity().getFlatNumber(), font));
        paragraph2.add(new Paragraph("Плательщик: " + userEntity.getUserInfo().getLastName() + " "
                + userEntity.getUserInfo().getFirstName() + " " + userEntity.getUserInfo().getSecondName(), font));
        paragraph0.add(new Paragraph("Квитанция № " + 1000 + COUNT + userEntity.getId(), font));
        paragraph1.add(new Paragraph("от " + sdf.format(new Date()), font));

        paragraph0.setAlignment(Element.ALIGN_RIGHT);
        paragraph1.setAlignment(Element.ALIGN_RIGHT);
        paragraph2.setAlignment(Element.ALIGN_RIGHT);
        paragraph3.setAlignment(Element.ALIGN_RIGHT);
        paragraph4.setAlignment(Element.ALIGN_CENTER);

        document.add(paragraph2);
        document.add(paragraph3);
        document.add(paragraph0);
        document.add(paragraph1);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(paragraph4);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
    }

    private void generateClosing(Document document, Font font) throws DocumentException {
        Paragraph paragraph0 = new Paragraph();
        Paragraph paragraph1 = new Paragraph();
        Paragraph paragraph2 = new Paragraph();

        paragraph0.add(new Paragraph("Дата     ________________", font));
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        paragraph1.add(new Paragraph("Подпись  ________________", font));
        paragraph2.add(new Paragraph("<<ОСББ>>", font));

        paragraph0.setAlignment(Element.ALIGN_RIGHT);
        paragraph1.setAlignment(Element.ALIGN_RIGHT);
        paragraph2.setAlignment(Element.ALIGN_CENTER);
        document.add(paragraph0);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(paragraph1);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(Chunk.NEWLINE);
        document.add(paragraph2);
    }

    private void generateTable(Document document, Font font, UserEntity userEntity) throws DocumentException {

        java.util.List<CurrentPayments> listPayments = userEntity.getCurrentPayments();
        for (CurrentPayments payment : listPayments) {
            long invoice = (payment.getCurrValue() - payment.getPrevValue()) * payment.getRate();
            long curr = payment.getCurrValue();
            long prev = payment.getPrevValue();
            long rate = payment.getRate();

            PdfPTable t = new PdfPTable(5);
            t.setSpacingBefore(25);
            t.setSpacingAfter(25);
            PdfPCell c1 = new PdfPCell(new Phrase("Сервис", font));
            PdfPCell c2 = new PdfPCell(new Phrase("Текущие", font));
            PdfPCell c3 = new PdfPCell(new Phrase("Прежние", font));
            PdfPCell c4 = new PdfPCell(new Phrase("Тариф", font));
            PdfPCell c5 = new PdfPCell(new Phrase("К оптале", font));
            c1.setHorizontalAlignment(Element.ALIGN_CENTER);
            c2.setHorizontalAlignment(Element.ALIGN_CENTER);
            c3.setHorizontalAlignment(Element.ALIGN_CENTER);
            c4.setHorizontalAlignment(Element.ALIGN_CENTER);
            c5.setHorizontalAlignment(Element.ALIGN_CENTER);
            t.addCell(c1);
            t.addCell(c2);
            t.addCell(c3);
            t.addCell(c4);
            t.addCell(c5);
            t.setHeaderRows(1);
            t.addCell(new PdfPCell(new Phrase(payment.getServicesEntity().getName(), font)));
            t.addCell("" + curr);
            t.addCell("" + prev);
            t.addCell("" + rate);
            t.addCell("" + invoice + " UAH");
            document.add(t);
        }
    }
}