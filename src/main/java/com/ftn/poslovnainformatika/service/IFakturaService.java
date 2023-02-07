package com.ftn.poslovnainformatika.service;

import com.ftn.poslovnainformatika.model.Faktura;

import java.util.List;

public interface IFakturaService {

    void save(Faktura faktura);

    void update(Faktura faktura);

    List<Faktura> findAll();

    Faktura getOne(long idFakture);

}
