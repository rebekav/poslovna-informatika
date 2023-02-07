package com.ftn.poslovnainformatika.service;

import com.ftn.poslovnainformatika.dto.NarudzbenicaDTO;
import com.ftn.poslovnainformatika.model.Narudzbenica;

import java.util.List;

public interface INarudzbenicaService {

    List<Narudzbenica> findAll();
    Narudzbenica getOne(long id);
    void save(Narudzbenica narudzbenica);
    void kreirajFakturuOdNarudzbenice(NarudzbenicaDTO narudzbenicaDTO, int poslednjaPoslovnjaGodina);

}
