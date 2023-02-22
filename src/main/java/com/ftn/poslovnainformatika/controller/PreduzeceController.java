package com.ftn.poslovnainformatika.controller;


import java.util.List;

import com.ftn.poslovnainformatika.dto.PreduzeceDTO;
import com.ftn.poslovnainformatika.mapper.MestoDTOToMesto;
import com.ftn.poslovnainformatika.mapper.MestoToMestoDTO;
import com.ftn.poslovnainformatika.mapper.PreduzeceDTOToPreduzece;
import com.ftn.poslovnainformatika.mapper.PreduzeceToPreduzeceDTO;
import com.ftn.poslovnainformatika.model.Mesto;
import com.ftn.poslovnainformatika.model.Preduzece;
import com.ftn.poslovnainformatika.service.IMestoService;
import com.ftn.poslovnainformatika.service.IPreduzeceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class PreduzeceController {

    @Autowired
    IMestoService mestoService;

    @Autowired
    IPreduzeceService preduzeceService;

    @Autowired
    PreduzeceDTOToPreduzece preduzeceDTOToPreduzece;

    @Autowired
    PreduzeceToPreduzeceDTO preduzeceToPreduzeceDTO;

    @Autowired
    MestoDTOToMesto mestoDTOToMesto;

    @Autowired
    MestoToMestoDTO mestoToMestoDTO;


    @GetMapping("/")
    public String vratiPreduzeca(Model model) {
        List<Preduzece> preduzece = preduzeceService.findAll();
        List<PreduzeceDTO> preduzeceDTO = preduzeceToPreduzeceDTO.konvertujEntityToDto(preduzece);
        model.addAttribute("preduzece", preduzeceDTO);
        return "preduzece";
    }

    @GetMapping("/preduzece/azuriraj/{id}")
    public String vratiPreduzeca(Model model, @PathVariable Long id) {
        Preduzece preduzece = preduzeceService.findOne(id);
        PreduzeceDTO preduzeceDTO = preduzeceToPreduzeceDTO.konvertujEntityToDto(preduzece);
        List<Mesto> listaMesta = mestoService.findAll();
        model.addAttribute("listaMesta", mestoToMestoDTO.konvertujEntityToDto(listaMesta));

        model.addAttribute("preduzece", preduzeceDTO);
        return "azurirajPreduzece";
    }

        @PostMapping("/preduzece/azuriraj")

        public String azurirajPreduzece(PreduzeceDTO preduzeceDTO) {

            preduzeceService.save(preduzeceDTOToPreduzece.konvertujDtoToEntity(preduzeceDTO));
                 return "redirect:/";
    }

}
