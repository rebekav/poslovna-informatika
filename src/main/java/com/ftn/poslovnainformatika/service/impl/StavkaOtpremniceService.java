package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.model.StavkaOtpremnice;
import com.ftn.poslovnainformatika.repository.StavkaOtpremniceRepository;
import com.ftn.poslovnainformatika.service.IStavkaOtpremniceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StavkaOtpremniceService implements IStavkaOtpremniceService {

    @Autowired
    StavkaOtpremniceRepository stavkaOtpremniceRepository;

    @Override
    public List<StavkaOtpremnice> findAll() {
        return stavkaOtpremniceRepository.findAllOtpremnice();    }

    @Override
    public void save(StavkaOtpremnice stavka) {
        stavkaOtpremniceRepository.save(stavka);
    }

    @Override
    public StavkaOtpremnice findOne(long id) {
        return stavkaOtpremniceRepository.getOne(id);
    }

    @Override
    public void izbrisiStavkuOtpremnice(StavkaOtpremnice stavka) {
        stavka.setObrisano(true);
        stavkaOtpremniceRepository.save(stavka);

    }

}
