package com.ftn.poslovnainformatika.controller;

import com.ftn.poslovnainformatika.dto.OtpremnicaDTO;
import com.ftn.poslovnainformatika.mapper.OtpremnicaDTOToOtpremnica;
import com.ftn.poslovnainformatika.mapper.OtpremnicaToOtpremnicaDTO;
import com.ftn.poslovnainformatika.mapper.PoslovniPartnerToPoslovniPartnerDTO;
import com.ftn.poslovnainformatika.mapper.StavkaOtpremniceDTOToStavkaOtpremnice;
import com.ftn.poslovnainformatika.mapper.StavkaOtpremniceToStavkaOtpremniceDTO;
import com.ftn.poslovnainformatika.model.Otpremnica;
import com.ftn.poslovnainformatika.model.PoslovnaGodina;
import com.ftn.poslovnainformatika.model.PoslovniPartner;
import com.ftn.poslovnainformatika.model.Preduzece;
import com.ftn.poslovnainformatika.model.Roba;
import com.ftn.poslovnainformatika.model.StavkaOtpremnice;
import com.ftn.poslovnainformatika.pdfExporter.PdfOtpremnicaExporter;
import com.ftn.poslovnainformatika.service.IOtpremnicaService;
import com.ftn.poslovnainformatika.service.IPoslovnaGodinaService;
import com.ftn.poslovnainformatika.service.IPoslovniPartnerService;
import com.ftn.poslovnainformatika.service.IPreduzeceService;
import com.ftn.poslovnainformatika.service.IRobaService;
import com.ftn.poslovnainformatika.service.IStavkaOtpremniceService;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class OtpremnicaController {

    @Autowired
    IOtpremnicaService otpremnicaService;

    @Autowired
    IStavkaOtpremniceService stavkaOtpremniceService;

    @Autowired
    IPoslovniPartnerService poslovniPartnerService;

    @Autowired
    IPreduzeceService preduzeceService;

    @Autowired
    IPoslovnaGodinaService poslovnaGodinaService;

    @Autowired
    IRobaService robaService;

    @Autowired
    OtpremnicaDTOToOtpremnica otpremnicaDTOToOtpremnica;

    @Autowired
    OtpremnicaToOtpremnicaDTO otpremnicaToOtpremnicaDTO;

    @Autowired
    StavkaOtpremniceDTOToStavkaOtpremnice stavkaOtpremniceDTOToStavkaOtpremnice;

    @Autowired
    StavkaOtpremniceToStavkaOtpremniceDTO stavkaOtpremniceToStavkaOtpremniceDTO;

    @Autowired
    PoslovniPartnerToPoslovniPartnerDTO poslovniPartnerToPoslovniPartnerDTO;

    @GetMapping("/otpremnice")
    public String vratiOtpremnice(Model model) {
        List<Otpremnica> otpremnice = otpremnicaService.findAll();
        model.addAttribute("otpremnice", otpremnicaToOtpremnicaDTO.konvertujEntityToDto(otpremnice));
        return "otpremnice";
    }

    @GetMapping("otpremnica/dodavanje")
    public String dodajOtpremnicu(Model model) {
        List<PoslovniPartner> listaPoslovniPartneri = poslovniPartnerService.findAll().stream()
                .filter(pp -> pp.getTipPoslovnogPartnera() == PoslovniPartner.TipPoslovnogPartnera.KUPAC)
                .collect(Collectors.toList());
        model.addAttribute("listaPoslovnihPartnera", poslovniPartnerToPoslovniPartnerDTO.konvertujEntityToDto(listaPoslovniPartneri));
        model.addAttribute("otpremnica", new OtpremnicaDTO());

        return "dodajOtpremnicu";
    }

    @PostMapping("otpremnica/dodavanje")
    public String dodajNarudzbenicu(OtpremnicaDTO narudzbenicaDTO) {
        List<Preduzece> preduzeca = preduzeceService.findAll();
        //	PoslovniPartner partner = poslovniPartnerService.findOne(narudzbenicaDTO.getTipNarudzbenice());
        PoslovnaGodina poslednjaPoslovnaGodina = poslovnaGodinaService.findByZakljucenaGodinaIsFalseAndObrisanoIsFalse().get(0);
        Otpremnica otpremnica = otpremnicaDTOToOtpremnica.konvertujDtoToEntity(narudzbenicaDTO);
        otpremnica.setPreduzece(preduzeca.get(0));
        otpremnica.setDatumOtpremnice(new Date());
        otpremnica.setObrisano(false);
        otpremnica.setPoslovnaGodina(poslednjaPoslovnaGodina);
        otpremnica.setBrojOtpremnice(poslednjaPoslovnaGodina.getOtpremnice().size() + 1);
        otpremnica.setTipOtpremnice(false);
        //	narudzbenica.setPoslovniPartner(partner);
        //narudzbenica.setTipNarudzbenice(TipNarudzbenice.valueOf(partner.getTipPoslovnogPartnera().getValue()));
        otpremnicaService.save(otpremnica);
        return "redirect:/otpremnice";
    }

    @GetMapping("otpremnica/detalji/{idotpremnice}")
    public String detaljiNarudzbenice(Model model, @PathVariable long idotpremnice) {
        Otpremnica otpremnica = otpremnicaService.getOne(idotpremnice);
        List<Roba> robe = robaService.findAll().stream()
                .filter(rb -> rb.getStavkeCenovnika().size() > 0)
                .collect(Collectors.toList());
        List<StavkaOtpremnice> stavkaOtpremnice = stavkaOtpremniceService.findAll().stream()
                .filter(so -> so.getOtpremnica().getId() == idotpremnice)
                .collect(Collectors.toList());
        model.addAttribute("otpremnica", otpremnicaToOtpremnicaDTO.konvertujEntityToDto(otpremnica));
        model.addAttribute("robe", robe);
        model.addAttribute("stavkeOtpremnice", stavkaOtpremnice);
        model.addAttribute("poslovniPartner", poslovniPartnerToPoslovniPartnerDTO.konvertujEntityToDto(otpremnica.getPoslovniPartner()));
        return "otpremnica_detalji";
    }

    @GetMapping("/otpremnica/kreirajFakturu/{otpremnicaId}")
    public String kreirajFakturuOdOtpremnice(Model model, @PathVariable long otpremnicaId) {
        PoslovnaGodina poslovnaGodina = poslovnaGodinaService.findByZakljucenaGodinaIsFalseAndObrisanoIsFalse().get(0);
        int poslednjaPoslovnjaGodina = poslovnaGodina.getFakture().size();

        Otpremnica otpremnica = otpremnicaService.getOne(otpremnicaId);
        OtpremnicaDTO otpremnicaDTO = otpremnicaToOtpremnicaDTO.konvertujEntityToDto(otpremnica);
        otpremnicaService.kreirajFakturuOdOtpremnice(otpremnicaDTO, poslednjaPoslovnjaGodina);
        return "redirect:/otpremnice";
    }

    @GetMapping("otpremnica/pdf/{otpremnicaId}")
    public void exportToPdf(HttpServletResponse response, @PathVariable long otpremnicaId) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        Otpremnica otpremnica = otpremnicaService.getOne(otpremnicaId);

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename="+ otpremnica.getBrojOtpremnice() + "_" + otpremnica.getPoslovnaGodina().getGodinaPoslovanja() + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<StavkaOtpremnice> stavkeOtpremnice = stavkaOtpremniceService.findAll().stream()
                .filter(so -> so.getOtpremnica().getId() == otpremnicaId)
                .collect(Collectors.toList());

        PdfOtpremnicaExporter exporter = new PdfOtpremnicaExporter(stavkeOtpremnice, otpremnica);
        exporter.export(response);
    }

}