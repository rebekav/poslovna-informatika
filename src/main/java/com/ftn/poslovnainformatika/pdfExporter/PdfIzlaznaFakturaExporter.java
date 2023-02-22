package com.ftn.poslovnainformatika.pdfExporter;

import com.ftn.poslovnainformatika.model.Faktura;
import com.ftn.poslovnainformatika.model.StavkaFakture;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import jakarta.servlet.http.HttpServletResponse;
import com.lowagie.text.Font;


import java.awt.*;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class PdfIzlaznaFakturaExporter {

    private List<StavkaFakture> stavkeFakture;
    private Faktura faktura;

    public PdfIzlaznaFakturaExporter(List<StavkaFakture> stavkeFakture, Faktura faktura) {
        this.stavkeFakture = stavkeFakture;
        this.faktura = faktura;
    }

    private void writeFakturaHeader(Document document) {

        PdfPTable table = new PdfPTable(new float[] { 12, 12 });
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(0);

        table.addCell("Izdavalac racuna: \n" + faktura.getPoslovniPartner().getNazivPoslovnogPartnera());
        table.addCell("Kupac: \n" + faktura.getPreduzece().getNazivPreduzeca());
        table.addCell("Mesto i adresa: \n" + faktura.getPoslovniPartner().getAdresa() + " " + " "
                + faktura.getPoslovniPartner().getMesto().getPostanskiBroj() + " "
                + faktura.getPoslovniPartner().getMesto().getGrad());
        table.addCell("Mesto i adresa: \n" + faktura.getPreduzece().getAdresa() + " " + " "
                + faktura.getPreduzece().getMesto().getPostanskiBroj() + " "
                + faktura.getPreduzece().getMesto().getGrad());
        table.addCell("PIB: \n" + faktura.getPoslovniPartner().getPIB());
        table.addCell("PIB: \n" + faktura.getPreduzece().getPIB());
        table.addCell("Tekuci racun: \n" + faktura.getPoslovniPartner().getTekuciRacun());
        table.addCell("Tekuci racun: \n" + faktura.getPreduzece().getTekuciRacun());

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

        cell.setPhrase(new Phrase("Sifra stavke", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Naziv robe", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Jednica mere", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Kolicina", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Stopa PDV", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Cena po komadu", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Rabat", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Osnovica", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("PDV", font));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Ukupan iznos", font));
        table.addCell(cell);
    }

    private void writeTableData(PdfPTable table) {

        int redniBroj = 1;

        for (StavkaFakture stavkaFakture : stavkeFakture) {
            table.addCell(String.valueOf(redniBroj));
            table.addCell(String.valueOf(stavkaFakture.getId()));
            table.addCell(stavkaFakture.getRoba().getNazivRobe());
            table.addCell(stavkaFakture.getRoba().getJedinicaMere());
            table.addCell(String.valueOf(stavkaFakture.getKolicina()));
            table.addCell(String.valueOf(stavkaFakture.getProcenatPDV()));
            table.addCell(String.valueOf(stavkaFakture.getCena()));
            table.addCell(String.valueOf(stavkaFakture.getRabat()));
            table.addCell(String.valueOf(stavkaFakture.getOsnovicaPDV()));
            table.addCell(String.valueOf(stavkaFakture.getIznosPDV()));
            table.addCell(String.valueOf(stavkaFakture.getUkupanIznos()));
            redniBroj++;
        }

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.MAGENTA);

        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        table.addCell("");
        cell.setPhrase(new Phrase("\u03a3"));
        table.addCell(cell);
        cell.setPhrase(new Phrase(String.valueOf(faktura.getRabat())));
        table.addCell(cell);
        cell.setPhrase(new Phrase(String.valueOf(faktura.getOsnovica())));
        table.addCell(cell);
        cell.setPhrase(new Phrase(String.valueOf(faktura.getUkupanPdv())));
        table.addCell(cell);
        cell.setPhrase(new Phrase(String.valueOf(faktura.getIznosZaPlacanje())));
        table.addCell(cell);
    }

    private void writeFakturaFooter(Document document) {

        PdfPTable table = new PdfPTable(new float[] { 12, 12 });
        table.setWidthPercentage(100);
        table.getDefaultCell().setBorder(0);

        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");

        table.addCell("Broj fakture: \n" + faktura.getId() + "/" + faktura.getPoslovnaGodina().getGodinaPoslovanja() + "\n");
        table.addCell("Rabat: \n" + faktura.getRabat() + "\n___________________________");
        table.addCell("Datum fakture: \n" + dateFormatter.format(faktura.getDatumFakture()) + "\n");
        table.addCell("Osnovica PDV: \n" + faktura.getOsnovica() + "\n___________________________");
        table.addCell("Datum valute: \n" + dateFormatter.format(faktura.getDatumValute()) + "\n");
        table.addCell("PDV: \n" + faktura.getUkupanPdv() + "\n___________________________");
        table.addCell("");
        table.addCell("Ukupno za placanje: \n" + faktura.getIznosZaPlacanje() + "\n___________________________");

        document.add(table);
    }

    public void export(HttpServletResponse response) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        document.open();
        Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
        font.setSize(14);
        font.setColor(Color.BLUE);

        Paragraph p = new Paragraph(
                "Faktura br. " + faktura.getBrojFakture() + "/" + faktura.getPoslovnaGodina().getGodinaPoslovanja(),
                font);
        p.setAlignment(Paragraph.ALIGN_CENTER);

        document.add(p);

        document.add(Chunk.NEWLINE);
        writeFakturaHeader(document);

        PdfPTable tableHead = new PdfPTable(2);
        tableHead.setWidthPercentage(100f);

        for (int i = 0; i < 2; i++) {
            document.add(Chunk.NEWLINE);
        }

        PdfPTable table = new PdfPTable(11);
        table.setWidthPercentage(100f);
//        table.setWidths(new float[] {1.5f, 3.5f, 3.0f, 3.0f, 1.5f});
        table.setSpacingBefore(5);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);

        document.add(Chunk.NEWLINE);

        writeFakturaFooter(document);

        document.close();

    }

}