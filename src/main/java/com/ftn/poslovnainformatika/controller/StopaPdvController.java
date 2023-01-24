package com.ftn.poslovnainformatika.controller;


import com.ftn.poslovnainformatika.dto.StopaPdvDTO;
import com.ftn.poslovnainformatika.mapper.PdvDTOToPdv;
import com.ftn.poslovnainformatika.mapper.PdvToPdvDTO;
import com.ftn.poslovnainformatika.mapper.StopaPdvDTOToStopaPdv;
import com.ftn.poslovnainformatika.mapper.StopaPdvToStopaPdvDTO;
import com.ftn.poslovnainformatika.model.Pdv;
import com.ftn.poslovnainformatika.model.StopaPdv;
import com.ftn.poslovnainformatika.service.IPdvService;
import com.ftn.poslovnainformatika.service.IStopaPdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

@Controller
public class StopaPdvController {

    @Autowired
    private IStopaPdvService stopaPdvService;

    @Autowired
    private IPdvService pdvService;

    @Autowired
    private StopaPdvDTOToStopaPdv stopaPdvDTOToStopaPdv;

    @Autowired
    private StopaPdvToStopaPdvDTO stopaPdvToStopaPdvDTO;

    @Autowired
    private PdvToPdvDTO pdvToPdvDTO;

    @Autowired
    private PdvDTOToPdv pdvDTOToPdv;


    @GetMapping("/stopaPdv")
    public String vratiStopePdv(Model model) {
        List<StopaPdv> stopaPdvLista = stopaPdvService.findAll();
        List<StopaPdvDTO> listaStopaPdvDTO = stopaPdvToStopaPdvDTO.konvertujEntityToDto(stopaPdvLista);
        model.addAttribute("listaStopaPdv", listaStopaPdvDTO);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        model.addAttribute("dateFormat", dateFormat);
        return "stopaPdv";
    }

    @GetMapping("/stopaPdv/azuriraj/{id}")
    public String vratiStopuPdvById(Model model, @PathVariable Long id) {
        StopaPdv stopaPdv = stopaPdvService.findOne(id);
        StopaPdvDTO stopaPdvDTO = stopaPdvToStopaPdvDTO.konvertujEntityToDto(stopaPdv);
        List<Pdv> listaPdv = pdvService.findAll();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        model.addAttribute("dateFormat", dateFormat);
        model.addAttribute("listaPdv", pdvToPdvDTO.konvertujEntityToDto(listaPdv));
        model.addAttribute("stopaPdv", stopaPdvDTO);
        return "azurirajStopuPdv";
    }

    @PostMapping("/stopaPdv/azuriraj")
    public String azurirajStopuPdv(StopaPdvDTO stopaPdvDTO) {
        stopaPdvService.save(stopaPdvDTOToStopaPdv.konvertujDtoToEntity(stopaPdvDTO));
        return "redirect:/stopaPdv";
    }


    @GetMapping("/stopaPdv/kreiraj")
    public String prikaziStopuPdv(Model model) {
        StopaPdvDTO stopaPdvDTO = new StopaPdvDTO();
        List<Pdv> listaPdv = pdvService.findAll();
        model.addAttribute("stopaPdv", stopaPdvDTO);
        model.addAttribute("listaPdv", pdvToPdvDTO.konvertujEntityToDto(listaPdv));
        return "kreirajStopuPdv";
    }

    @PostMapping("/stopaPdv/kreiraj")
    public String kreirajStopuPdv(StopaPdvDTO stopaPdvDTO) {
        stopaPdvService.save(stopaPdvDTOToStopaPdv.konvertujDtoToEntity(stopaPdvDTO));
        return "redirect:/stopaPdv";
    }

    @DeleteMapping("/stopaPdv/izbrisi")
    public String izbrisiStopuPdv(Long stopaPdvIdDelete) {
        StopaPdv stopaPdv = stopaPdvService.findOne(stopaPdvIdDelete);;
        stopaPdvService.izbrisiStopuPdv(stopaPdv);
        return "redirect:/stopaPdv";
    }

}
