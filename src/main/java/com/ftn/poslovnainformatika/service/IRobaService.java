package com.ftn.poslovnainformatika.service;

import com.ftn.poslovnainformatika.model.Roba;

import java.util.List;

public interface IRobaService {

    List<Roba> findAll();

    Roba findOne(Long id);

    void save(Roba roba);

    void izbrisiRobu(Roba roba);

    void izbrisiRobuByGrupaRobeId(Long grupaRobeId);

}
