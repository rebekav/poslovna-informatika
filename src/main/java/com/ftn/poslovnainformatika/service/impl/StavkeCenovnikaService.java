package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.model.StavkeCenovnika;
import com.ftn.poslovnainformatika.repository.StavkaCenovnikaRepository;
import com.ftn.poslovnainformatika.service.IStavkeCenovnikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StavkeCenovnikaService implements IStavkeCenovnikaService {

    @Autowired
    StavkaCenovnikaRepository stavkaCenovnikaRepository;

    @Override
    public List<StavkeCenovnika> findStavkeCenovnikaByCenovnikId(long id) {
        return stavkaCenovnikaRepository.findAll().stream()
                .filter(sc -> sc.getCenovnik().getId() == id)
                .collect(Collectors.toList());
    }

    @Override
    public void save(StavkeCenovnika stavka) {
        stavkaCenovnikaRepository.save(stavka);

    }

    @Override
    public List<StavkeCenovnika> findStavkeCenovnikaByRobaId(long id) {
        return stavkaCenovnikaRepository.findAll().stream()
                .filter(sc -> sc.getRoba().getId() == id)
                .collect(Collectors.toList());
    }



}