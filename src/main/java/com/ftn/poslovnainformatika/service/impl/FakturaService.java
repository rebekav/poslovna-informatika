package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.model.Faktura;
import com.ftn.poslovnainformatika.model.StavkaFakture;
import com.ftn.poslovnainformatika.repository.FakturaRepository;
import com.ftn.poslovnainformatika.service.IFakturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FakturaService implements IFakturaService {

    @Autowired
    FakturaRepository fakturaRepository;

    @Override
    public void update(Faktura faktura) {
        double ukupanPdv = 0;
        double ukupanIznos = 0;
        double iznosBezRabata = 0;
        double  rabat = 0;
        double osnovica = 0;
        for(StavkaFakture sf : faktura.getStavkeFakture()) {
            rabat += sf.getRabat();
            iznosBezRabata += sf.getKolicina() * sf.getCena();
            osnovica += sf.getOsnovicaPDV();
            ukupanPdv += sf.getIznosPDV();
            ukupanIznos += sf.getUkupanIznos();
        }

        faktura.setOsnovica(osnovica);
        faktura.setUkupanPdv(ukupanPdv);
        faktura.setIznosBezRabata(iznosBezRabata);
        faktura.setRabat(rabat);
        faktura.setIznosZaPlacanje(ukupanIznos);

        save(faktura);

    }

    @Override
    public void save(Faktura faktura) {
        fakturaRepository.save(faktura);

    }

    @Override
    public List<Faktura> findAll() {
        return fakturaRepository.findAll();
    }

    @Override
    public Faktura getOne(long idFakture) {
        return fakturaRepository.getOne(idFakture);
    }



}