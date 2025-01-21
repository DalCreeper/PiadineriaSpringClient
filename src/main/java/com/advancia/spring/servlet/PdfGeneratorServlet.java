package com.advancia.spring.servlet;

import com.advancia.spring.soapclient.MeatBase;
import com.advancia.spring.soapclient.OptionalElements;
import com.advancia.spring.soapclient.Piadina;
import com.advancia.spring.soapclient.Sauces;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/generatePdf")
public class PdfGeneratorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        @SuppressWarnings("unchecked")
		List<Piadina> piadinas = (List<Piadina>) request.getSession().getAttribute("piadinas");
        
        if(piadinas == null || piadinas.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No piadinas available to generate the PDF.");
            return;
        }
        
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", "attachment; filename=PiadineriaAdvancia_menu.pdf");

        try {
        	Document document = new Document(PageSize.A4.rotate());
            PdfWriter.getInstance(document, response.getOutputStream());
            document.open();

            PdfPTable headerTable = new PdfPTable(1);
            headerTable.setWidthPercentage(100);

            PdfPCell headerCell = new PdfPCell(new Phrase("Piadinas Menu", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 24, BaseColor.WHITE)));
            headerCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            headerCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            headerCell.setBackgroundColor(new BaseColor(0x3B, 0x4C, 0x54));
            headerCell.setPadding(15);
            headerCell.setBorder(Rectangle.NO_BORDER);
            headerTable.addCell(headerCell);
            document.add(headerTable);

            document.add(Chunk.NEWLINE);

            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setWidths(new int[] {3, 3, 6, 6, 6, 3, 4});

            table.getDefaultCell().setBackgroundColor(new BaseColor(0xFF, 0xFF, 0xFF));
            table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
            table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);
            table.getDefaultCell().setPadding(10);

            addTableHeader(table);
            
            System.out.println("Piadinas: " + piadinas);
            for(Piadina piadina : piadinas) {
            	if(piadina != null) {
                    addTableRow(table, piadina);
                }
            }

            document.add(table);
            document.close();
        } catch(DocumentException e) {
            throw new IOException("Error generating PDF", e);
        }
    }

    private void addTableHeader(PdfPTable table) {
        Stream.of("Name", "Dough", "Meat Base", "Sauces", "Optional Elements", "Price", "Employee")
            .forEach(columnTitle -> {
            	PdfPCell header = new PdfPCell();
                header.setBackgroundColor(new BaseColor(0x2A, 0x36, 0x3B));
                header.setHorizontalAlignment(Element.ALIGN_CENTER);
                header.setVerticalAlignment(Element.ALIGN_MIDDLE);
                header.setPadding(10);
                header.setPhrase(new Phrase(columnTitle, FontFactory.getFont(FontFactory.HELVETICA_BOLD, 12, BaseColor.WHITE)));
                table.addCell(header);
            }
        );
    }

    private void addTableRow(PdfPTable table, Piadina piadina) {
        table.addCell(piadina.getName());
        table.addCell(piadina.getDough() != null ? piadina.getDough().getType() : "N/A");
        table.addCell(String.join(", ", piadina.getMeatBase().stream().map(MeatBase::getType).collect(Collectors.toList())));
        table.addCell(String.join(", ", piadina.getSauces().stream().map(Sauces::getType).collect(Collectors.toList())));
        table.addCell(String.join(", ", piadina.getOptionalElements().stream().map(OptionalElements::getType).collect(Collectors.toList())));
        table.addCell(String.format("%.2f", piadina.getPrice()) + " €");
        table.addCell(piadina.getEmployee() != null ? piadina.getEmployee().getName() + " " + piadina.getEmployee().getSurname(): "N/A");
    }
}