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
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
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

    @GetMapping("/knjigaIzlaznihFaktura")
    public String getIzlazneFakture(Model model) {
        if (!model.containsAttribute("fakture")) {
            List<Faktura> fakture = fakturaService.findAll();
            List<Faktura> faktureFilter = fakture.stream().filter(f -> f.isVrstaFakture() == true)
                    .collect(Collectors.toList());
            model.addAttribute("fakture", fakturaToFakturaDTO.konvertujEntityToDto(faktureFilter));
        }
        return "knjigaIzlaznihFaktura";
    }

    @GetMapping("/knjigaUlaznihFaktura")
    public String getUlazneFakture(Model model) {
        if (!model.containsAttribute("fakture")) {
            List<Faktura> fakture = fakturaService.findAll();
            List<Faktura> faktureFilter = fakture.stream().filter(f -> f.isVrstaFakture() == false)
                    .collect(Collectors.toList());
            model.addAttribute("fakture", fakturaToFakturaDTO.konvertujEntityToDto(faktureFilter));
        }
        return "knjigaUlaznihFaktura";
    }

    @RequestMapping(value = "/prikaziKnjiguFaktura", method = RequestMethod.POST, params = "search")
    public String vratiKnjiguIzlaznihFaktura(Model model, String startDate, String endDate, boolean tipFakture)
            throws ParseException {

        List<Faktura> fakture = fakturaService.findAll().stream().filter(f -> f.isVrstaFakture() == tipFakture).collect(Collectors.toList());
        String pocetak = null;
        String kraj = null;
        List<Faktura> listaFaktura = new ArrayList<>();
        if((startDate !=null || !startDate.isEmpty()) && (endDate != null && !endDate.isEmpty())) {
            Date startDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            Date endDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
            endDateUtil = new Date(endDateUtil.getTime() + (1000 * 60 * 60 * 24));



            for (Faktura faktura : fakture) {
                if (faktura.getDatumFakture().after(startDateUtil) && faktura.getDatumFakture().before(endDateUtil)) {
                    listaFaktura.add(faktura);
                }
            }

            pocetak = startDate.substring(0, 10);
            kraj = endDate.substring(0, 10);
        }
        else {
            listaFaktura = fakture;
        }

        model.addAttribute("fakture", fakturaToFakturaDTO.konvertujEntityToDto(listaFaktura));
        model.addAttribute("pocetak", pocetak);
        model.addAttribute("kraj", kraj);

        if (tipFakture) {
            return getIzlazneFakture(model);
        }
        return getUlazneFakture(model);
    }

    @RequestMapping(value = "/prikaziKnjiguFaktura", method = RequestMethod.POST, params = "print")
    public String stampajKniguFaktura(Model model, String startDate, String endDate, boolean tipFakture)
            throws ParseException {

        List<Faktura> fakturedb = fakturaService.findAll().stream().filter(f -> f.isVrstaFakture() == tipFakture).collect(Collectors.toList());
        String pocetak = null;
        String kraj = null;
        Date startDateUtil = null;
        Date endDateUtil = null;
        List<Faktura> fakture = new ArrayList<>();
        if((startDate !=null || !startDate.isEmpty()) && (endDate != null && !endDate.isEmpty())) {
            startDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(startDate);
            endDateUtil = new SimpleDateFormat("yyyy-MM-dd").parse(endDate);
            endDateUtil = new Date(endDateUtil.getTime() + (1000 * 60 * 60 * 24));



            for (Faktura faktura : fakturedb) {
                if (faktura.getDatumFakture().after(startDateUtil) && faktura.getDatumFakture().before(endDateUtil)) {
                    fakture.add(faktura);
                }
            }

        }
        else {
            fakture = fakturedb;
            startDateUtil = new Date();
            endDateUtil = new Date();
        }

        JRBeanCollectionDataSource faktureJasper = new JRBeanCollectionDataSource(fakture);

        /* Map to hold Jasper report Parameters */
        Map<String, Object> params = new HashMap<>();

        params.put("fakture", faktureJasper);

        try {

            if(!tipFakture) {


                /* Reading jrxml file and creating JasperDesign object */
                InputStream is = this.getClass().getResource("/knjigaUlaznihFaktura.jrxml").openStream();

                JasperDesign jasperDesign = JRXmlLoader.load(is);

                /* Compiling jrxml with the help of JasperReport class */
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

                /* Using jasperReport object to generate PDF */
                JasperPrint jp = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
                ByteArrayInputStream bais = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));
                JasperExportManager.exportReportToPdfFile(jp,"D:\\knjigaulaznihfaktura.pdf");
                return "knjigaUlaznihFaktura";
            }else {
                /* Reading jrxml file and creating JasperDesign object */
                InputStream is = this.getClass().getResource("/knjigaIzlaznihFaktura.jrxml").openStream();

                JasperDesign jasperDesign = JRXmlLoader.load(is);

                /* Compiling jrxml with the help of JasperReport class */
                JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

                /* Using jasperReport object to generate PDF */
                JasperPrint jp = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
                ByteArrayInputStream bais = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));
                JasperExportManager.exportReportToPdfFile(jp,"D:\\knjigaizlaznihfaktura.pdf");
                return "knjigaIzlaznihFaktura";
            }
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return "fakture";

    }

    @GetMapping("/pdfFakture/{idFakture}")
    public String pdfIzvestajFaktura(Model model, @PathVariable int idFakture) {

        Faktura faktura = fakturaService.getOne(idFakture);



        List<StavkaFakture> stavkeFakture = stavkeFaktureService.findAll().stream()
                .filter(sn -> sn.getFaktura().getId() == idFakture).collect(Collectors.toList());



        /* Convert the list above to JRBeanCollectionDataSource */
        JRBeanCollectionDataSource stavkeFaktureJasper = new JRBeanCollectionDataSource(stavkeFakture);

        /* Map to hold Jasper report Parameters */
        Map<String, Object> params  = new HashMap<>();

        params.put("faktura", faktura);
        params.put("stavkeFakture", stavkeFaktureJasper);

        try {

            /* Reading jrxml file and creating JasperDesign object */
            InputStream is = this.getClass().getResource("/faktura.jrxml").openStream();

            JasperDesign jasperDesign = JRXmlLoader.load(is);

            /* Compiling jrxml with the help of JasperReport class */
            JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);

            /* Using jasperReport object to generate PDF */
            JasperPrint jp = JasperFillManager.fillReport(jasperReport, params, new JREmptyDataSource());
            ByteArrayInputStream bais = new ByteArrayInputStream(JasperExportManager.exportReportToPdf(jp));
            JasperExportManager.exportReportToPdfFile(jp,"D:\\faktura.pdf");
        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return "fakture";
    }
}
