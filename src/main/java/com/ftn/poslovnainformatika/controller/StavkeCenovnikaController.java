package com.ftn.poslovnainformatika.controller;

import com.ftn.poslovnainformatika.model.Cenovnik;
import com.ftn.poslovnainformatika.model.Roba;
import com.ftn.poslovnainformatika.model.StavkeCenovnika;
import com.ftn.poslovnainformatika.service.ICenovnikService;
import com.ftn.poslovnainformatika.service.IRobaService;
import com.ftn.poslovnainformatika.service.IStavkeCenovnikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StavkeCenovnikaController {

    @Autowired
    IRobaService robaService;

    @Autowired
    ICenovnikService cenovnikService;

    @Autowired
    IStavkeCenovnikaService stavkaCenovnikaService;

    @GetMapping("/stavkeCenovnika")
    public void sacuvajStavkuNarudzbenice(@RequestParam(value = "cenovnikId") long cenovnikId, @RequestParam(value = "cena") int cena, @RequestParam(value = "robaId") long robaId) {

        Cenovnik cenovnik = cenovnikService.findOne(cenovnikId);
        Roba roba = robaService.findOne(robaId);

        StavkeCenovnika stavka = new StavkeCenovnika();
        stavka.setCena(cena);
        stavka.setCenovnik(cenovnik);
        stavka.setRoba(roba);
        stavka.setObrisano(false);
        stavkaCenovnikaService.save(stavka);
    }

}