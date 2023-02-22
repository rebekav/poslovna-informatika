package com.ftn.poslovnainformatika.service;

import com.ftn.poslovnainformatika.model.StavkaNarudzbenice;

import java.util.List;

public interface IStavkaNarudzbeniceService {

    void save(StavkaNarudzbenice stavkaNarudzbenice);
    List<StavkaNarudzbenice> findAll(Long narudzbenica);
    List<StavkaNarudzbenice> findAll();

    StavkaNarudzbenice findOne(long id);
    void izbrisiStavkuNarudzbenice(StavkaNarudzbenice stavka);
}
