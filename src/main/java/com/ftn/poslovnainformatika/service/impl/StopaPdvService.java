package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.model.StopaPdv;
import com.ftn.poslovnainformatika.repository.StopaPdvRepository;
import com.ftn.poslovnainformatika.service.IGrupaRobeService;
import com.ftn.poslovnainformatika.service.IStopaPdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class StopaPdvService implements IStopaPdvService {

    @Autowired
    StopaPdvRepository stopaPdvRepository;

    @Autowired
    private IGrupaRobeService grupaRobeService;

    @Override
    public StopaPdv findOne(Long id) {
        StopaPdv stopaPdv = stopaPdvRepository.getOne(id);
        if(stopaPdv != null) {
            return stopaPdv;
        }
        else {
            throw new RuntimeException("Nije pronadjena stopa pdv");
        }
    }

    @Override
    public List<StopaPdv> findAll() {

        return stopaPdvRepository.findAll();
    }

    @Override
    public void save(StopaPdv stopaPDV) {
        stopaPdvRepository.save(stopaPDV);
    }

    @Override
    public void izbrisiStopuPdv(StopaPdv stopaPdv) {
        stopaPdv.setObrisano(true);
        stopaPdvRepository.save(stopaPdv);
    }

}
