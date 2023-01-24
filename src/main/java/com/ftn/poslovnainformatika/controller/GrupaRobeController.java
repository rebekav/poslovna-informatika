package com.ftn.poslovnainformatika.controller;

import com.ftn.poslovnainformatika.dto.GrupaRobeDTO;
import com.ftn.poslovnainformatika.mapper.GrupaRobeDTOToGrupaRobe;
import com.ftn.poslovnainformatika.mapper.GrupaRobeToGrupaRobeDTO;
import com.ftn.poslovnainformatika.mapper.PreduzeceToPreduzeceDTO;
import com.ftn.poslovnainformatika.mapper.StopaPdvToStopaPdvDTO;
import com.ftn.poslovnainformatika.model.GrupaRobe;
import com.ftn.poslovnainformatika.model.Preduzece;
import com.ftn.poslovnainformatika.model.StopaPdv;
import com.ftn.poslovnainformatika.service.IGrupaRobeService;
import com.ftn.poslovnainformatika.service.IPreduzeceService;
import com.ftn.poslovnainformatika.service.IRobaService;
import com.ftn.poslovnainformatika.service.IStopaPdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller
public class GrupaRobeController {

    @Autowired
    private IGrupaRobeService grupaRobeService;

    @Autowired
    private IRobaService robaService;

    @Autowired
    private IStopaPdvService stopaPdvService;

    @Autowired
    private IPreduzeceService preduzeceService;

    @Autowired
    private GrupaRobeToGrupaRobeDTO grupaRobeToGrupaRobeDTO;

    @Autowired
    private GrupaRobeDTOToGrupaRobe grupaRobeDTOToGrupaRobe;

    @Autowired
    private PreduzeceToPreduzeceDTO preduzeceToPreduzeceDTO;

    @Autowired
    private StopaPdvToStopaPdvDTO stopaPdvToStopaPdvDTO;


    @GetMapping("/grupaRobe")
    public String vratiSveGrupeRobe(Model model) {
        List<GrupaRobe> grupaRobeLista = grupaRobeService.findAll();
        List<GrupaRobeDTO> listaGrupaRobeDTO = grupaRobeToGrupaRobeDTO.konvertujEntityToDto(grupaRobeLista);
        model.addAttribute("listaGrupaRobe", listaGrupaRobeDTO);
        return "grupaRobe";
    }

    @GetMapping("/grupaRobe/azuriraj/{id}")
    public String vratiGrupuRobeById(Model model, @PathVariable Long id) {
        GrupaRobe grupaRobe = grupaRobeService.findOne(id);
        GrupaRobeDTO grupaRobeDTO = grupaRobeToGrupaRobeDTO.konvertujEntityToDto(grupaRobe);
        dodajAtributeUModel(model, grupaRobeDTO);
        return "azurirajGrupuRobe";
    }

    @PutMapping("/grupaRobe/azuriraj")
    public String azurirajGrupuRobe(GrupaRobeDTO grupaRobeDTO) {
        grupaRobeService.save(grupaRobeDTOToGrupaRobe.konvertujDtoToEntity(grupaRobeDTO));
        return "redirect:/grupaRobe";
    }

    @GetMapping("/grupaRobe/kreiraj")
    public String prikaziGrupuRobe(Model model) {
        GrupaRobeDTO grupaRobeDTO = new GrupaRobeDTO();
        dodajAtributeUModel(model, grupaRobeDTO);
        return "kreirajGrupuRobe";
    }

    @PostMapping("/grupaRobe/kreiraj")
    public String kreirajGrupuRobe (GrupaRobeDTO grupaRobeDTO) {
        grupaRobeService.save(grupaRobeDTOToGrupaRobe.konvertujDtoToEntity(grupaRobeDTO));
        return "redirect:/grupaRobe";
    }

    @DeleteMapping("/grupaRobe/izbrisi")
    public String izbrisiGrupuRobe(Long grupaRobeIdDelete) {
        GrupaRobe grupaRobe = grupaRobeService.findOne(grupaRobeIdDelete);;
        grupaRobeService.izbrisiGrupuRobe(grupaRobe);
        robaService.izbrisiRobuByGrupaRobeId(grupaRobeIdDelete);
        return "redirect:/grupaRobe";
    }

    public void dodajAtributeUModel (Model model, GrupaRobeDTO grupaRobeDTO) {
        List<Preduzece> listaPreduzeca = preduzeceService.findAll();
        List<StopaPdv> listaStopaPdv = stopaPdvService.findAll();
        model.addAttribute("grupaRobe", grupaRobeDTO);
        model.addAttribute("listaPreduzeca", preduzeceToPreduzeceDTO.konvertujEntityToDto(listaPreduzeca));
        model.addAttribute("listaStopaPdv", stopaPdvToStopaPdvDTO.konvertujEntityToDto(listaStopaPdv));
    }

}
