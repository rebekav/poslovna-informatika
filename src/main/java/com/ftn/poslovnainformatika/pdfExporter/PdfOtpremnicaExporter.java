package com.ftn.poslovnainformatika.pdfExporter;

import com.ftn.poslovnainformatika.model.Otpremnica;
import com.ftn.poslovnainformatika.model.StavkaOtpremnice;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.lowagie.text.pdf.draw.LineSeparator;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PdfOtpremnicaExporter {

    private List<StavkaOtpremnice> stavkeOtpremnice;
    private Otpremnica otpremnica;

    public PdfOtpremnicaExporter(List<StavkaOtpremnice> stavkeOtpremnice, Otpremnica otpremnica) {
        this.stavkeOtpremnice = stavkeOtpremnice;
        this.otpremnica = otpremnica;
    }

    private void writeOtpremnicaHeader(Document document) {

        PdfPTable table = new PdfPTable(new float[] { 12, 12 });
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(0);

        table.addCell(otpremnica.getPreduzece().getNazivPreduzeca());
        table.addCell("Telefon: " + otpremnica.getPreduzece().getTelefon());
        table.addCell(otpremnica.getPreduzece().getAdresa() + " " + " "
                + otpremnica.getPreduzece().getMesto().getPostanskiBroj() + " "
                + otpremnica.getPreduzece().getMesto().getGrad());
        table.addCell("Tekuci racun: " + otpremnica.getPreduzece().getTekuciRacun());
        table.addCell("PIB: " + otpremnica.getPreduzece().getPIB());
        table.addCell("email: " + otpremnica.getPreduzece().getEmailAdresa());

        document.add(table);
    }

    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(5);

        Font font = FontFactory.getFont(FontFactory.HELVETICA);
        font.setSize(12);
        font.setColor(Color.black);

        cell.setPhrase(new Phrase("R br.", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Naziv robe", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Jednica mere", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Cena", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Kolicina", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Napomena", font));
        table.addCell(cell);

    }

    private void writeTableData(PdfPTable table) {

        int redniBroj = 1;

        for (StavkaOtpremnice stavkaOtpremnice : stavkeOtpremnice) {
            table.addCell(String.valueOf(redniBroj));
            table.addCell(stavkaOtpremnice.getRoba().getNazivRobe());
            table.addCell(stavkaOtpremnice.getRoba().getJedinicaMere());
            table.addCell(String.valueOf(stavkaOtpremnice.getCena()));
            table.addCell(String.valueOf(stavkaOtpremnice.getKolicina()));
            table.addCell("");
            redniBroj++;
        }
    }

    private void writeOtpremnicaSecondHeader(Document document) {

        PdfPTable table = new PdfPTable(new float[] { 12, 12 });
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(0);

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

        String currentDateTime = dateFormatter.format(new Date());

        table.addCell("Broj otpremnice: " + otpremnica.getBrojOtpremnice() + "/"
                + otpremnica.getPoslovnaGodina().getGodinaPoslovanja() + "\n");
        table.addCell("Datum izdavanja: " + currentDateTime);
        table.addCell("Kupac: " + otpremnica.getPoslovniPartner().getNazivPoslovnogPartnera());
        table.addCell(" ");
        table.addCell("Adresa isporuke: \n" + otpremnica.getPoslovniPartner().getAdresa() + " "
                + otpremnica.getPoslovniPartner().getMesto().getPostanskiBroj() + " "
                + otpremnica.getPoslovniPartner().getMesto().getGrad());
        table.addCell(" ");

        document.add(table);
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(14);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph("Otpremnica br. " + otpremnica.getBrojOtpremnice() + "/"
                + otpremnica.getPoslovnaGodina().getGodinaPoslovanja(), font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        document.add(Chunk.NEWLINE);
        writeOtpremnicaHeader(document);

        LineSeparator ls = new LineSeparator();
        document.add(new Chunk(ls));
        document.add(Chunk.NEWLINE);

        writeOtpremnicaSecondHeader(document);

        document.add(Chunk.NEWLINE);

        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100f);

        table.setSpacingBefore(5);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.add(Chunk.NEWLINE);

        document.close();

    }

}