package com.ftn.poslovnainformatika.controller;

import com.ftn.poslovnainformatika.dto.StavkaNarudzbeniceDTO;
import com.ftn.poslovnainformatika.mapper.StavkaNarudzbeniceToStavkaNarudzbeniceDTO;
import com.ftn.poslovnainformatika.model.Narudzbenica;
import com.ftn.poslovnainformatika.model.Roba;
import com.ftn.poslovnainformatika.model.StavkaNarudzbenice;
import com.ftn.poslovnainformatika.service.INarudzbenicaService;
import com.ftn.poslovnainformatika.service.IRobaService;
import com.ftn.poslovnainformatika.service.IStavkaNarudzbeniceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class StavkaNarudzbeniceController {

    @Autowired
    IStavkaNarudzbeniceService stavkaNarudzbeniceService;

    @Autowired
    IRobaService robaService;

    @Autowired
    INarudzbenicaService narudzbenicaService;

    @Autowired
    StavkaNarudzbeniceDTOToStavkaNarudzbenice stavkaNarudzbeniceDTOToStavkaNarudzbenice;

    @Autowired
    StavkaNarudzbeniceToStavkaNarudzbeniceDTO stavkaNarudzbeniceToStavkaNarudzbeniceDTO;

    @PostMapping("/stavkaNarudzbenice")
    public void sacuvajStavkuNarudzbenice(@RequestBody StavkaNarudzbeniceDTO stavkaNarudzbenice) {
        //StavkaNarudzbenice stavka = stavkaNarudzbeniceDTOToStavkaNarudzbenice.konvertujDtoToEntity(dto);
        Roba roba = robaService.findOne(stavkaNarudzbenice.getRoba());
        Narudzbenica narudzbenica = narudzbenicaService.getOne(stavkaNarudzbenice.getNarudzbenica());
        StavkaNarudzbenice stavka = new StavkaNarudzbenice();
        stavka.setJedinicaMere(stavkaNarudzbenice.getJedinicaMere());
        stavka.setKolicina(stavkaNarudzbenice.getKolicina());
        stavka.setOpis(stavkaNarudzbenice.getOpis());
        stavka.setRoba(roba);
        stavka.setNarudzbenica(narudzbenica);
        stavka.setObrisano(false);

        stavkaNarudzbeniceService.save(stavka);
    }

    @GetMapping("/stavkaNarudzbenice/obrisi/{id}/{narudzbenicaId}")
    public String izbrisiStavkuNarudzbenice(@PathVariable long id, @PathVariable long narudzbenicaId) {
        StavkaNarudzbenice stavka = stavkaNarudzbeniceService.findOne(id);
        stavkaNarudzbeniceService.izbrisiStavkuNarudzbenice(stavka);
        return "redirect:/narudzbenica/detalji/{narudzbenicaId}";
    }

}
