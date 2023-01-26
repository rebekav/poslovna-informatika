package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.model.Narudzbenica;
import com.ftn.poslovnainformatika.repository.NarudzbenicaRepository;
import com.ftn.poslovnainformatika.service.INarudzbenicaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NarudzbenicaService implements INarudzbenicaService {

    @Autowired
    NarudzbenicaRepository narudzbenicaRepository;

    @Override
    public List<Narudzbenica> findAll() {
        return narudzbenicaRepository.findAll();
    }

    @Override
    public Narudzbenica getOne(long id) {
        return narudzbenicaRepository.getOne(id);
    }

    @Override
    public void save(Narudzbenica narudzbenica) {
        narudzbenicaRepository.save(narudzbenica);

    }
}
