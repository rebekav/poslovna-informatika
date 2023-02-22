package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.model.StavkaNarudzbenice;
import com.ftn.poslovnainformatika.repository.StavkaNarudzbeniceRepository;
import com.ftn.poslovnainformatika.service.IStavkaNarudzbeniceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class StavkaNarudzbeniceService implements IStavkaNarudzbeniceService {

    @Autowired
    StavkaNarudzbeniceRepository stavkaNarudzbeniceRepository;

    @Override
    public void save(StavkaNarudzbenice stavkaNarudzbenice) {
        stavkaNarudzbeniceRepository.save(stavkaNarudzbenice);
    }

    @Override
    public List<StavkaNarudzbenice> findAll(Long narudzbenica) {
        return stavkaNarudzbeniceRepository.findAllByNarudzbenicaId(narudzbenica);
    }

    @Override
    public List<StavkaNarudzbenice> findAll() {
        return stavkaNarudzbeniceRepository.findAllStavkeNarudzbenice();
    }

    @Override
    public StavkaNarudzbenice findOne(long id) {
        return stavkaNarudzbeniceRepository.getOne(id);
    }

    @Override
    public void izbrisiStavkuNarudzbenice(StavkaNarudzbenice stavka) {
        stavka.setObrisano(true);
        stavkaNarudzbeniceRepository.save(stavka);

    }
}
