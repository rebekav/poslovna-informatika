package com.ftn.poslovnainformatika.controller;

import com.ftn.poslovnainformatika.dto.StavkaOtpremniceDTO;
import com.ftn.poslovnainformatika.model.Otpremnica;
import com.ftn.poslovnainformatika.model.Roba;
import com.ftn.poslovnainformatika.model.StavkaOtpremnice;
import com.ftn.poslovnainformatika.service.IOtpremnicaService;
import com.ftn.poslovnainformatika.service.IRobaService;
import com.ftn.poslovnainformatika.service.IStavkaOtpremniceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class StavkeOtpremniceController {

    @Autowired
    IRobaService robaService;

    @Autowired
    IOtpremnicaService otpremniceService;

    @Autowired
    IStavkaOtpremniceService stavkaOtpremniceService;

    @PostMapping("/stavkaOtpremnice")
    public void sacuvajStavkuOtpremnice(@RequestBody StavkaOtpremniceDTO stavkaOtpremnice) {
        //StavkaNarudzbenice stavka = stavkaNarudzbeniceDTOToStavkaNarudzbenice.konvertujDtoToEntity(dto);
        Roba roba = robaService.findOne(stavkaOtpremnice.getRoba());
        Otpremnica otpremnica = otpremniceService.getOne(stavkaOtpremnice.getOtpremnica());
        StavkaOtpremnice stavka = new StavkaOtpremnice();
        stavka.setJedinicaMere(stavkaOtpremnice.getJedinicaMere());
        stavka.setKolicina(stavkaOtpremnice.getKolicina());
        stavka.setOpis(stavkaOtpremnice.getOpis());
        stavka.setCena(stavkaOtpremnice.getCena());
        stavka.setUkupanIznos(stavkaOtpremnice.getUkupanIznos());
        stavka.setRoba(roba);
        stavka.setOtpremnica(otpremnica);
        stavka.setObrisano(false);

        stavkaOtpremniceService.save(stavka);
    }

    @GetMapping("/stavkaOtpremnice/obrisi/{id}/{otpremnicaId}")
    public String izbrisiStavkuOtpremnice(@PathVariable long id, @PathVariable long otpremnicaId) {
        StavkaOtpremnice stavka = stavkaOtpremniceService.findOne(id);
        stavkaOtpremniceService.izbrisiStavkuOtpremnice(stavka);
        return "redirect:/otpremnica/detalji/{otpremnicaId}";
    }
}