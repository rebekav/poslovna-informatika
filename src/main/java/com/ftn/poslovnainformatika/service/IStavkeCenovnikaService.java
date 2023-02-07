package com.ftn.poslovnainformatika.service;

import com.ftn.poslovnainformatika.model.StavkeCenovnika;

import java.util.List;

public interface IStavkeCenovnikaService {
    List<StavkeCenovnika> findStavkeCenovnikaByCenovnikId(long id);

    void save(StavkeCenovnika stavka);

    List<StavkeCenovnika> findStavkeCenovnikaByRobaId(long id);
}