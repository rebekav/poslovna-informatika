package com.ftn.poslovnainformatika.controller;

import com.ftn.poslovnainformatika.dto.FakturaDTO;
import com.ftn.poslovnainformatika.mapper.*;
import com.ftn.poslovnainformatika.model.Faktura;
import com.ftn.poslovnainformatika.model.StavkaFakture;
import com.ftn.poslovnainformatika.pdfExporter.PdfIzlaznaFakturaExporter;
import com.ftn.poslovnainformatika.pdfExporter.PdfUlaznaFakturaExporter;
import com.ftn.poslovnainformatika.service.IFakturaService;
import com.ftn.poslovnainformatika.service.IStavkeFakture;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class FakturaController {

    @Autowired
    IFakturaService fakturaService;

    @Autowired
    IStavkeFakture stavkeFaktureService;

    @Autowired
    FakturaToFakturaDTO fakturaToFakturaDTO;

    @Autowired
    FakturaDTOToFaktura fakturaDTOToFaktura;

    @Autowired
    StavkaFaktureToStavkaFaktureDTO stavkaFaktureToStavkaFaktureDTO;

    @Autowired
    PoslovniPartnerToPoslovniPartnerDTO poslovniPartnerToPoslovniPartnerDTO;

    @Autowired
    PoslovniPartnerDTOToPoslovniPartner poslovniPartnerDTOToPoslovniPartner;


    @GetMapping("/fakture")
    public String getAll(@RequestParam(value = "tipFakture", defaultValue = "false") boolean tip, Model model) {
        List<Faktura> fakture = fakturaService.findAll();
        boolean finalTip = Boolean.valueOf(tip);
        List<Faktura> faktureFilter = fakture.stream().filter(f -> f.isVrstaFakture() == finalTip)
                .collect(Collectors.toList());
        model.addAttribute("tipFakture", finalTip);
        model.addAttribute("fakture", fakturaToFakturaDTO.konvertujEntityToDto(faktureFilter));
        return "fakture";

    }

    @GetMapping("faktura/detalji/{idFakture}")
    public String detaljiNarudzbenice(Model model, @PathVariable int idFakture) {
        Faktura faktura = fakturaService.getOne(idFakture);
        List<StavkaFakture> stavkeFakture = stavkeFaktureService.findAll().stream()
                .filter(sn -> sn.getFaktura().getId() == idFakture)
                .collect(Collectors.toList());
        FakturaDTO dto = fakturaToFakturaDTO.konvertujEntityToDto(faktura);
        model.addAttribute("faktura", dto);
        model.addAttribute("stavkeFakture", stavkaFaktureToStavkaFaktureDTO.konvertujEntityToDto(stavkeFakture));
        model.addAttribute("poslovniPartner", poslovniPartnerToPoslovniPartnerDTO.konvertujEntityToDto(faktura.getPoslovniPartner()));
        return "faktura_detalji";
    }

    @GetMapping("/faktura/pdf/{idFakture}")
    public void exportToPDF(HttpServletResponse response, @PathVariable int idFakture)
            throws DocumentException, IOException {
        response.setContentType("application/pdf");

        Faktura faktura = fakturaService.getOne(idFakture);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=" + faktura.getBrojFakture() + "_"
                + faktura.getPoslovnaGodina().getGodinaPoslovanja() + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<StavkaFakture> stavkeFakture = stavkeFaktureService.findAll().stream()
                .filter(sn -> sn.getFaktura().getId() == idFakture).collect(Collectors.toList());

        FakturaDTO fakturaDTO = fakturaToFakturaDTO.konvertujEntityToDto(faktura);

        if (fakturaDTO.isVrstaFakture()) {
            PdfUlaznaFakturaExporter exporter = new PdfUlaznaFakturaExporter(stavkeFakture, faktura);
            exporter.export(response);
        } else {
            PdfIzlaznaFakturaExporter exporter = new PdfIzlaznaFakturaExporter(stavkeFakture, faktura);
            exporter.export(response);
        }

    }
}
