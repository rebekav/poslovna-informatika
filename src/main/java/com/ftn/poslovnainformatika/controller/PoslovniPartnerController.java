package com.ftn.poslovnainformatika.controller;


import org.springframework.ui.Model;
import com.ftn.poslovnainformatika.dto.PoslovniPartnerDTO;
import com.ftn.poslovnainformatika.mapper.MestoToMestoDTO;

import com.ftn.poslovnainformatika.mapper.PoslovniPartnerDTOToPoslovniPartner;
import com.ftn.poslovnainformatika.mapper.PoslovniPartnerToPoslovniPartnerDTO;
import com.ftn.poslovnainformatika.model.PoslovniPartner.*;
import com.ftn.poslovnainformatika.model.*;
import com.ftn.poslovnainformatika.service.IMestoService;
import com.ftn.poslovnainformatika.service.IPoslovniPartnerService;
import com.ftn.poslovnainformatika.service.IPreduzeceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class PoslovniPartnerController {

    @Autowired
    private IPoslovniPartnerService poslovniPartnerServiceInterface;

    @Autowired
    IMestoService mestoService;

    @Autowired
    IPreduzeceService preduzeceService;

    @Autowired
    private PoslovniPartnerDTOToPoslovniPartner dtoToPoslovniPartner;

    @Autowired
    private PoslovniPartnerToPoslovniPartnerDTO poslovniPartnertoDto;

    @Autowired
    MestoToMestoDTO mestoToMestoDTO;

    @GetMapping("/poslovniPartneri")
    public String getAll(@RequestParam(value = "tipPoslovnogPartnera", defaultValue = "3") int tip, Model model) {
        List<PoslovniPartner> poslovniPartneri = poslovniPartnerServiceInterface.findAll();
        tip = Integer.valueOf(tip);
        if (tip == 3) {
            List<PoslovniPartnerDTO> poslovniPartnerDTO = poslovniPartnertoDto.konvertujEntityToDto(poslovniPartneri);
            model.addAttribute("tipPoslovnogPartnera", tip);
            model.addAttribute("poslovniPartneri", poslovniPartnerDTO);

            return "poslovniPartneri";
        }

        int finalTip = tip;
        List<PoslovniPartner> poslovniPartnerFilter = poslovniPartneri.stream()
                .filter(p -> p.getTipPoslovnogPartnera() == TipPoslovnogPartnera.valueOf(finalTip))
                .collect(Collectors.toList());
        model.addAttribute("tipPoslovnogPartneraFilter", finalTip);
        model.addAttribute("poslovniPartneriFilter", poslovniPartnertoDto.konvertujEntityToDto(poslovniPartnerFilter));

        return "poslovniPartneri";

    }

    @GetMapping("/poslovniPartneri/azuriraj/{id}")
    public String vratiPreduzeca(Model model, @PathVariable Long id) {
        PoslovniPartner poslovniPartner = poslovniPartnerServiceInterface.findOne(id);
        PoslovniPartnerDTO poslovniPartnerDTO = poslovniPartnertoDto.konvertujEntityToDto(poslovniPartner);
        List<Mesto> listaMesta = mestoService.findAll();
        model.addAttribute("listaMesta", mestoToMestoDTO.konvertujEntityToDto(listaMesta));
        model.addAttribute("poslovniPartner", poslovniPartnerDTO);

        return "azurirajPoslovnogPartnera";
    }

    @PostMapping("/poslovniPartneri/azuriraj")
    public String azurirajPoslovnogPartnera(PoslovniPartnerDTO poslovniPartnerDTO) {
        PoslovniPartner partner = dtoToPoslovniPartner.konvertujDTOToEntity(poslovniPartnerDTO);
        partner.setPreduzece(preduzeceService.findAll().get(0));
        poslovniPartnerServiceInterface.save(partner);

        return "redirect:/poslovniPartneri";
    }

    @GetMapping("poslovniPartner/dodavanje")
    public String dodajPoslovnogPartnera(Model model) {
        List<Mesto> listaMesta = mestoService.findAll();
        model.addAttribute("listaMesta", mestoToMestoDTO.konvertujEntityToDto(listaMesta));
        model.addAttribute("poslovniPartner", new PoslovniPartnerDTO());

        return "dodajPoslovnogPartnera";
    }

    @PostMapping("poslovniPartner/dodavanje")
    public String dodajPoslovnogPartnera(PoslovniPartnerDTO poslovniPartnerDTO) {
        List<Preduzece> preduzeca = preduzeceService.findAll();
        PoslovniPartner partner = dtoToPoslovniPartner.konvertujDTOToEntity(poslovniPartnerDTO);
        partner.setPreduzece(preduzeca.get(0));
        partner.setObrisano(false);
        poslovniPartnerServiceInterface.save(partner);

        return "redirect:/poslovniPartneri";
    }

}