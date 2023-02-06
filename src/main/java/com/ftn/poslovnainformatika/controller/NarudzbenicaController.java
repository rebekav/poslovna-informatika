package com.ftn.poslovnainformatika.controller;

import com.ftn.poslovnainformatika.dto.NarudzbenicaDTO;
import com.ftn.poslovnainformatika.mapper.NarudzbenicaDtoToNarudzbenica;
import com.ftn.poslovnainformatika.mapper.NarudzbenicaToNarudzbenicaDTO;
import com.ftn.poslovnainformatika.mapper.PoslovniPartnerToPoslovniPartnerDTO;
import com.ftn.poslovnainformatika.mapper.RobaToRobaDTO;
import com.ftn.poslovnainformatika.model.*;
import com.ftn.poslovnainformatika.model.Narudzbenica.TipNarudzbenice;
import com.ftn.poslovnainformatika.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class NarudzbenicaController {

    @Autowired
    IPoslovnaGodinaService poslovnaGodinaService;

    @Autowired
    INarudzbenicaService narudzbenicaService;

    @Autowired
    IStavkaNarudzbeniceService stavkaNarudzbeniceService;

    @Autowired
    IPoslovniPartnerService poslovniPartnerService;

    @Autowired
    IPreduzeceService preduzeceService;

    @Autowired
    IRobaService robaService;

    @Autowired
    NarudzbenicaDtoToNarudzbenica narudzbenicaDTOToNarudzbenica;

    @Autowired
    NarudzbenicaToNarudzbenicaDTO narudzbenicaToNarudzbenicaDTO;

    @Autowired
    PoslovniPartnerToPoslovniPartnerDTO poslovniPartnerToPoslovniPartnerDTO;

    @Autowired
    RobaToRobaDTO robaToRobaDTO;

    @GetMapping("/narudzbenice")
    public String getAll(@RequestParam(value = "tipNarudzbenice", defaultValue = "3") int tip, Model model) {
        List<Narudzbenica> narudzbenice = narudzbenicaService.findAll();
        tip = Integer.valueOf(tip);
        if (tip == 3) {
            List<NarudzbenicaDTO> narudzbeniceDTO = narudzbenicaToNarudzbenicaDTO.konvertujEntityToDto(narudzbenice);
            model.addAttribute("tipNarudzbenice", tip);
            model.addAttribute("narudzbenice", narudzbeniceDTO);

            return "narudzbenice";
        }

        int finalTip = tip;
        List<Narudzbenica> naruzbenicaFilter = narudzbenice.stream()
                .filter(n -> n.getTipNarudzbenice() == TipNarudzbenice.valueOf(finalTip))
                .collect(Collectors.toList());
        model.addAttribute("tipNarudzbenice", finalTip);
        model.addAttribute("narudzbeniceFilter", narudzbenicaToNarudzbenicaDTO.konvertujEntityToDto(naruzbenicaFilter));

        return "narudzbenice";

    }

    @GetMapping("narudzbenica/dodavanje/{tipNarudzbenice}")
    public String dodajNarudzbenicu(Model model, @PathVariable int tipNarudzbenice) {
        List<PoslovniPartner> listaPoslovniPartneri = poslovniPartnerService.findAll().stream()
                .filter(pp -> pp.getTipPoslovnogPartnera() == PoslovniPartner.TipPoslovnogPartnera.valueOf(tipNarudzbenice))
                .collect(Collectors.toList());
        model.addAttribute("tipNarudzbenice", tipNarudzbenice);
        model.addAttribute("listaPoslovnihPartnera", poslovniPartnerToPoslovniPartnerDTO.konvertujEntityToDto(listaPoslovniPartneri));
        model.addAttribute("narudzbenica", new NarudzbenicaDTO());

        return "dodajNarudzbenicu";
    }

    @PostMapping("narudzbenica/dodavanje")
    public String dodajNarudzbenicu(NarudzbenicaDTO narudzbenicaDTO) {
        List<Preduzece> preduzeca = preduzeceService.findAll();
        //	PoslovniPartner partner = poslovniPartnerService.findOne(narudzbenicaDTO.getTipNarudzbenice());
        PoslovnaGodina poslednjaPoslovnaGodina = poslovnaGodinaService.findByZakljucenaGodinaIsFalseAndObrisanoIsFalse().get(0);
        Narudzbenica narudzbenica = narudzbenicaDTOToNarudzbenica.konvertujDtoToEntity(narudzbenicaDTO);
        narudzbenica.setPreduzece(preduzeca.get(0));
        narudzbenica.setDatumNarudzbenice(new Date());
        narudzbenica.setObrisano(false);
        narudzbenica.setPoslovnaGodina(poslednjaPoslovnaGodina);
        narudzbenica.setBrojNarudzbenice(poslednjaPoslovnaGodina.getNarudzbenice().size() + 1);
        int fs = narudzbenicaDTO.getPoslovniPartner().getTipPoslovnogPartnera().getValue();
        narudzbenica.setTipNarudzbenice(TipNarudzbenice.valueOf(narudzbenicaDTO.getPoslovniPartner().getTipPoslovnogPartnera().getValue()));
        //	narudzbenica.setPoslovniPartner(partner);
        //narudzbenica.setTipNarudzbenice(TipNarudzbenice.valueOf(partner.getTipPoslovnogPartnera().getValue()));
        narudzbenicaService.save(narudzbenica);
        return "redirect:/narudzbenice";
    }

    @GetMapping("narudzbenica/detalji/{idNarudzbenice}")
    public String detaljiNarudzbenice(Model model, @PathVariable int idNarudzbenice) {
        long re = 5;
        Narudzbenica narudzbenica = narudzbenicaService.getOne(idNarudzbenice);
        List<Roba> robe = robaService.findAll();
        List<StavkaNarudzbenice> stavkeNarudzbenice = stavkaNarudzbeniceService.findAll().stream()
                .filter(sn -> sn.getNarudzbenica().getId() == idNarudzbenice)
                .collect(Collectors.toList());
        model.addAttribute("narudzbenica", narudzbenicaToNarudzbenicaDTO.konvertujEntityToDto(narudzbenica));
        model.addAttribute("robe", robe);
        model.addAttribute("stavkeNarudzbenice", stavkeNarudzbenice);
        model.addAttribute("poslovniPartner", poslovniPartnerToPoslovniPartnerDTO.konvertujEntityToDto(narudzbenica.getPoslovniPartner()));
        return "narudzbenica_detalji";
    }

}
