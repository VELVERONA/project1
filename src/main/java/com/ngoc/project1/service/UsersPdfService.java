package com.ngoc.project1.service;

import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.ngoc.project1.entity.OurUsers;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.engine.jdbc.Size;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class UsersPdfService {
    private List<OurUsers> usersList;

    public UsersPdfService (List<OurUsers> usersList)
    {
        this.usersList = usersList;
    }

    private void writeTableHeader(PdfPTable table){
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.blue);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setColor(Color.white);

        cell.setPhrase(new Phrase("User Id",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Email",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Name",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("City",font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Roles",font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table){
        for (OurUsers user : usersList) {
            table.addCell(String.valueOf(user.getId()));
            table.addCell(user.getEmail());
            table.addCell(user.getName());
            table.addCell(user.getCity());
            table.addCell(user.getRole());
        }
    }
    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(18);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("List of users", font);
        p.setAlignment(Element.ALIGN_CENTER);
        document.add(p);
        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 1.5f , 3.0f});
        table.setSpacingBefore(10);
        writeTableHeader(table);
        writeTableData(table);
        document.add(table);
        document.close();
    }
}
