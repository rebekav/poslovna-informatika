package com.ftn.poslovnainformatika.controller;

import com.ftn.poslovnainformatika.dto.RobaDTO;
import com.ftn.poslovnainformatika.mapper.GrupaRobeDTOToGrupaRobe;
import com.ftn.poslovnainformatika.mapper.GrupaRobeToGrupaRobeDTO;
import com.ftn.poslovnainformatika.mapper.RobaDTOToRoba;
import com.ftn.poslovnainformatika.mapper.RobaToRobaDTO;
import com.ftn.poslovnainformatika.model.GrupaRobe;
import com.ftn.poslovnainformatika.model.Roba;
import com.ftn.poslovnainformatika.service.IGrupaRobeService;
import com.ftn.poslovnainformatika.service.IRobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class RobaController {

    @Autowired
    private IRobaService robaService;

    @Autowired
    private IGrupaRobeService grupaRobeService;

    @Autowired
    private RobaToRobaDTO robaToRobaDTO;

    @Autowired
    private RobaDTOToRoba robaDTOToRoba;

    @Autowired
    private GrupaRobeToGrupaRobeDTO grupaRobeToGrupaRobeDTO;

    @Autowired
    private GrupaRobeDTOToGrupaRobe grupaRobeDTOToGrupaRobe;


    @GetMapping("/roba")
    public String vratiSveProizvode(Model model) {
        List<Roba> listaRobe = robaService.findAll();
        List<RobaDTO> listaRobeDTO = robaToRobaDTO.konvertujEntityToDto(listaRobe);
        model.addAttribute("listaRobe", listaRobeDTO);
        return "roba";
    }

    @GetMapping("/roba/azuriraj/{id}")
    public String vratiRobuById(Model model, @PathVariable Long id) {
        Roba roba = robaService.findOne(id);
        RobaDTO robaDTO = robaToRobaDTO.konvertujEntityToDto(roba);
        dodajAtributeUModel(model, robaDTO);
        return "azurirajRobu";
    }

    @PutMapping("/roba/azuriraj")
    public String azurirajGrupuRobe(RobaDTO robaDTO) {
        robaService.save(robaDTOToRoba.konvertujDtoToEntity(robaDTO));
        return "redirect:/roba";
    }

    @GetMapping("/roba/kreiraj")
    public String prikaziRobu(Model model) {
        RobaDTO robaDTO = new RobaDTO();
        dodajAtributeUModel(model, robaDTO);
        return "kreirajRobu";
    }

    @PostMapping("/roba/kreiraj")
    public String kreirajRobu (RobaDTO robaDTO) {
        robaService.save(robaDTOToRoba.konvertujDtoToEntity(robaDTO));
        return "redirect:/roba";
    }

    @DeleteMapping("/roba/izbrisi")
    public String izbrisiArtikalRobe(Long robaIdDelete) {
        Roba roba = robaService.findOne(robaIdDelete);;
        robaService.izbrisiRobu(roba);
        return "redirect:/roba";
    }

    public void dodajAtributeUModel (Model model, RobaDTO robaDTO) {
        List<GrupaRobe> listaGrupeRobe = grupaRobeService.findAll();
        model.addAttribute("roba", robaDTO);
        model.addAttribute("listaGrupeRobe", grupaRobeToGrupaRobeDTO.konvertujEntityToDto(listaGrupeRobe));
    }

}
