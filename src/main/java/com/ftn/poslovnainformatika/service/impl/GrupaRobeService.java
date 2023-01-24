package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.model.GrupaRobe;
import com.ftn.poslovnainformatika.repository.GrupaRobeRepository;
import com.ftn.poslovnainformatika.service.IGrupaRobeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrupaRobeService implements IGrupaRobeService {

    @Autowired
    GrupaRobeRepository grupaRobeRepository;

    @Override
    public GrupaRobe findOne(Long id) {
        GrupaRobe grupaRobe = grupaRobeRepository.getOne(id);
        if(grupaRobe != null) {
            return grupaRobe;
        }
        else {
            throw new RuntimeException("Nije pronadjena grupa robe");
        }
    }

    @Override
    public List<GrupaRobe> findAll() {

        return grupaRobeRepository.findAll();
    }

    @Override
    public void save(GrupaRobe grupaRobe) {

        grupaRobeRepository.save(grupaRobe);
    }

    @Override
    public void izbrisiGrupuRobe(GrupaRobe grupaRobe) {
        grupaRobe.setObrisano(true);
        grupaRobeRepository.save(grupaRobe);
    }
}
