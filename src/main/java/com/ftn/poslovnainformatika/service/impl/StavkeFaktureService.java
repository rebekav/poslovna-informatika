package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.model.StavkaFakture;
import com.ftn.poslovnainformatika.repository.StavkaFaktureRepository;
import com.ftn.poslovnainformatika.service.IStavkeFakture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StavkeFaktureService implements IStavkeFakture {

    @Autowired
    StavkaFaktureRepository stavkaFaktureRepository;

    @Override
    public void save(StavkaFakture novaStavkaFakture) {
        stavkaFaktureRepository.save(novaStavkaFakture);

    }

    @Override
    public List<StavkaFakture> findAll() {
        return stavkaFaktureRepository.findAll();
    }

}
