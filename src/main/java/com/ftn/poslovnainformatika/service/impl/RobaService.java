package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.model.Roba;
import com.ftn.poslovnainformatika.repository.RobaRepository;
import com.ftn.poslovnainformatika.service.IRobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RobaService implements IRobaService {

    @Autowired
    RobaRepository robaRepository;

    @Override
    public Roba findOne(Long id) {
        Roba roba = robaRepository.getOne(id);
        if(roba != null) {
            return roba;
        }
        else {
            throw new RuntimeException("Nije pronadjen trazeni proizvod");
        }
    }

    @Override
    public List<Roba> findAll() {

        return robaRepository.findAll();
    }

    @Override
    public void save(Roba roba) {
        robaRepository.save(roba);
    }

    @Override
    public void izbrisiRobu(Roba roba) {
        roba.setObrisano(true);
        robaRepository.save(roba);
    }

    @Override
    public void izbrisiRobuByGrupaRobeId(Long grupaRobeId) {

        List<Roba> listaRobe = robaRepository.findByGrupaRobeId(grupaRobeId);
        for (Roba roba : listaRobe) {
            roba.setObrisano(true);
            robaRepository.save(roba);
        }

    }
}