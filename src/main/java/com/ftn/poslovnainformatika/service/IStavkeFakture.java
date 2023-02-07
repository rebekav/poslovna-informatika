package com.ftn.poslovnainformatika.service;

import com.ftn.poslovnainformatika.model.StavkaFakture;

import java.util.List;

public interface IStavkeFakture {

    void save(StavkaFakture novaStavkaFakture);

    List<StavkaFakture> findAll();

}
