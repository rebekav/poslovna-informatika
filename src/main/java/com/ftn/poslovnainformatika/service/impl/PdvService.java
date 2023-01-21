package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.model.Pdv;
import com.ftn.poslovnainformatika.repository.PdvRepository;
import com.ftn.poslovnainformatika.service.IPdvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PdvService implements IPdvService {

    @Autowired
    PdvRepository pdvRepository;

    @Override
    public Pdv findOne(Long id) {
        Pdv pdv = pdvRepository.getOne(id);
        if(pdv != null) {
            return pdv;
        }
        else {
            throw new RuntimeException("Nije pronadjen pdv");
        }
    }

    @Override
    public List<Pdv> findAll() {

        return pdvRepository.findAll();
    }

}
