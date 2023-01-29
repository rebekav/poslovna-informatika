package com.ftn.poslovnainformatika.service;

import com.ftn.poslovnainformatika.model.Mesto;

import java.util.List;

public interface IMestoService {

    Mesto findOne(Long id);
    List<Mesto> findAll();

    void save(Mesto mesto);

    void izbrisiMesto(Mesto mesto);
}
