package com.ftn.poslovnainformatika.controller;

import com.ftn.poslovnainformatika.dto.OtpremnicaDTO;
import com.ftn.poslovnainformatika.mapper.*;
import com.ftn.poslovnainformatika.model.*;
import com.ftn.poslovnainformatika.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
        List<Roba> robe = robaService.findAll();
        List<StavkaOtpremnice> stavkaOtpremnice = stavkaOtpremniceService.findAll().stream()
                .filter(so -> so.getOtpremnica().getId() == idotpremnice)
                .collect(Collectors.toList());
        model.addAttribute("otpremnica", otpremnicaToOtpremnicaDTO.konvertujEntityToDto(otpremnica));
        model.addAttribute("robe", robe);
        model.addAttribute("stavkeOtpremnice", stavkaOtpremnice);
        model.addAttribute("poslovniPartner", poslovniPartnerToPoslovniPartnerDTO.konvertujEntityToDto(otpremnica.getPoslovniPartner()));
        return "otpremnica_detalji";
    }

    @GetMapping("/otpremnica/kreirajFakturu/{idOtpremnice}")
    public String kreirajFakturuOdOtpremnice(Model model, @PathVariable long idOtpremnice) {
        PoslovnaGodina poslovnaGodina = poslovnaGodinaService.findByZakljucenaGodinaIsFalseAndObrisanoIsFalse().get(0);
        int poslednjaPoslovnjaGodina = poslovnaGodina.getFakture().size();

        Otpremnica otpremnica = otpremnicaService.getOne(idOtpremnice);
        OtpremnicaDTO otpremnicaDTO = otpremnicaToOtpremnicaDTO.konvertujEntityToDto(otpremnica);
        otpremnicaService.kreirajFakturuOdOtpremnice(otpremnicaDTO, poslednjaPoslovnjaGodina);
        return "redirect:/otpremnice";
    }

}