package com.ftn.poslovnainformatika.controller;

import com.ftn.poslovnainformatika.dto.MestoDTO;
import com.ftn.poslovnainformatika.mapper.MestoDTOToMesto;
import com.ftn.poslovnainformatika.mapper.MestoToMestoDTO;
import com.ftn.poslovnainformatika.model.Mesto;
import com.ftn.poslovnainformatika.service.IMestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MestoController {

    @Autowired
    IMestoService mestoService;

    @Autowired
    MestoDTOToMesto mestoDTOToMesto;

    @Autowired
    MestoToMestoDTO mestoToMestoDTO;


    @GetMapping("/mesto")
    public String vratiSvaMesta(Model model) {
        List<Mesto> listaMesta = mestoService.findAll();
        List<MestoDTO> listaMestaDTO = mestoToMestoDTO.konvertujEntityToDto(listaMesta);
        model.addAttribute("listaMesta", listaMestaDTO);
        return "mesto";
    }

    @GetMapping("/mesto/azuriraj/{id}")
    public String vratiMesto(Model model, @PathVariable Long id) {
        Mesto mesto = mestoService.findOne(id);
        MestoDTO mestoDTO = mestoToMestoDTO.konvertujEntityToDto(mesto);
        model.addAttribute("mesto", mestoDTO);
        return "azurirajMesto";
    }

    @PutMapping("/mesto/azuriraj")
    public String azurirajMesto(MestoDTO mestoDTO) {
        mestoService.save(mestoDTOToMesto.konvertujDtoToEntity(mestoDTO));
        return "redirect:/mesto";
    }

    @GetMapping("/mesto/kreiraj")
    public String prikaziMesto(Model model) {
        Mesto mesto = new Mesto();
        MestoDTO mestoDTO = mestoToMestoDTO.konvertujEntityToDto(mesto);
        model.addAttribute("mesto", mestoDTO);
        return "kreirajMesto";
    }

    @PostMapping("/mesto/kreiraj")
    public String kreirajMesto(MestoDTO mestoDTO) {
        mestoService.save(mestoDTOToMesto.konvertujDtoToEntity(mestoDTO));
        return "redirect:/mesto";
    }

    @DeleteMapping("/mesto/izbrisi")
    public String izbrisiMesto(Long mestoIdDelete) {
        Mesto mesto = mestoService.findOne(mestoIdDelete);
        mestoService.izbrisiMesto(mesto);
        return "redirect:/mesto";
    }

}
