package com.ftn.poslovnainformatika.controller;

import com.ftn.poslovnainformatika.dto.CenovnikDTO;
import com.ftn.poslovnainformatika.dto.StavkaCenovnikaDTO;
import com.ftn.poslovnainformatika.mapper.*;
import com.ftn.poslovnainformatika.model.Cenovnik;
import com.ftn.poslovnainformatika.model.PoslovniPartner;
import com.ftn.poslovnainformatika.model.Preduzece;
import com.ftn.poslovnainformatika.model.StavkeCenovnika;
import com.ftn.poslovnainformatika.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Date;
import java.util.Calendar;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CenovnikController {

    @Autowired
    ICenovnikService cenovnikService;

    @Autowired
    IStavkeCenovnikaService stavkeCenovnikaService;

    @Autowired
    IPoslovniPartnerService poslovniPartnerService;

    @Autowired
    IPreduzeceService preduzeceService;

    @Autowired
    IRobaService robaService;

    @Autowired
    CenovnikToCenovnikDTO cenovnikToCenovnikDTO;

    @Autowired
    CenovnikDTOCenovnik cenovnikDTOCenovnik;

    @Autowired
    StavkaCenovnikaDTOToStavkaCenovnika stavkaCenovnikaDTOToStavkaCenovnika;

    @Autowired
    StavkaCenovnikaToStavkaCenovnikaDTO stavkaCenovnikaToStavkaCenovnikaDTO;

    @Autowired
    PoslovniPartnerToPoslovniPartnerDTO poslovniPartnerToPoslovniPartnerDTO;

    @Autowired
    PoslovniPartnerDTOToPoslovniPartner poslovniPartnerDTOToPoslovniPartner;

    @Autowired
    RobaToRobaDTO robaToRobaDTO;

    @GetMapping("cenovnici")
    public String vratiCenovnike(Model model) {
        List<Cenovnik> cenovnici = cenovnikService.findAll();
        List<CenovnikDTO> cenovniciDTO = cenovnikToCenovnikDTO.konvertujEntityToDto(cenovnici);
        model.addAttribute("cenovnici", cenovniciDTO);
        return "cenovnici";
    }

    @GetMapping("cenovnici/dodavanje")
    public String dodajCenovnik(Model model) {

        List<PoslovniPartner> poslovniPartneri = poslovniPartnerService.findAll();
        model.addAttribute("poslovniPartneri", poslovniPartnerToPoslovniPartnerDTO.konvertujEntityToDto(poslovniPartneri));
        model.addAttribute("cenovnik", new CenovnikDTO());
        return "dodajCenovnik";
    }

    @PostMapping("cenovnik/dodavanje")
    public String dodajCenovnik(CenovnikDTO cenovnikDto) {
        List<Preduzece> preduzeca = preduzeceService.findAll();
        List<Cenovnik> listaCenovnika = cenovnikService.findAll().stream()
                .filter(c -> c.getPoslovniPartner().getId() == cenovnikDto.getPoslovniPartner().getId())
                .collect(Collectors.toList());
        for(Cenovnik c: listaCenovnika) {
            c.setKrajRokaTrajanja(new Date(Calendar.getInstance().getTimeInMillis()));
            c.setObrisano(true);
            cenovnikService.save(c);
        }
        Cenovnik cenovnik = cenovnikDTOCenovnik.konvertujDtoToEntity(cenovnikDto);
        cenovnik.setPreduzece(preduzeca.get(0));
        cenovnik.setObrisano(false);
        cenovnikService.save(cenovnik);

        return "redirect:/cenovnici";
    }

    @GetMapping("cenovnik/detalji/{idcenovnika}")
    public String detaljiCenovnika(Model model, @PathVariable long idcenovnika) {
        Cenovnik cenovnik = cenovnikService.findOne(idcenovnika);
        model.addAttribute("listRoba", robaToRobaDTO.konvertujEntityToDto(robaService.findAll()));
        model.addAttribute("cenovnik", cenovnikToCenovnikDTO.konvertujEntityToDto(cenovnik));
        List<StavkeCenovnika> stavkeCenovnika = stavkeCenovnikaService.findStavkeCenovnikaByCenovnikId(idcenovnika);
        List<StavkaCenovnikaDTO> stavkeCenovnikaDto = stavkaCenovnikaToStavkaCenovnikaDTO.konvertujEntityToDto(stavkeCenovnika);
        model.addAttribute("stavkeCenovnika", stavkeCenovnikaDto);
        return "cenovnik_detalji";
    }


}
