package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.model.Mesto;
import com.ftn.poslovnainformatika.repository.MestoRepository;
import com.ftn.poslovnainformatika.service.IMestoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MestoService implements IMestoService {

    @Autowired
    MestoRepository mestoRepository;

    @Override
    public Mesto findOne(Long id) {
        Mesto mesto = mestoRepository.getOne(id);
        if(mesto != null) {
            return mesto;
        }
        else {
            throw new RuntimeException("Nije pronadjeno trazeno mesto");
        }
    }

    @Override
    public List<Mesto> findAll() {

        return mestoRepository.findAll();
    }
}
