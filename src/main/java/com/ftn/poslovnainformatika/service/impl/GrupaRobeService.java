package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.model.GrupaRobe;
import com.ftn.poslovnainformatika.repository.GrupaRobeRepository;
import com.ftn.poslovnainformatika.service.IGrupaRobeService;
import com.ftn.poslovnainformatika.service.IRobaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GrupaRobeService implements IGrupaRobeService {

    @Autowired
    GrupaRobeRepository grupaRobeRepository;

    @Autowired
    private IRobaService robaService;

    @Override
    public GrupaRobe findOne(Long id) {
        GrupaRobe grupaRobe = grupaRobeRepository.getOne(id);
        if(grupaRobe != null) {
            return grupaRobe;
        } else {
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
        robaService.izbrisiRobuByGrupaRobeId(grupaRobe.getId());
        grupaRobeRepository.save(grupaRobe);
    }

    @Override
    public void izbrisiGrupuRobeByPdvId(Long pdvId) {
        List<GrupaRobe> listaGrupeRobe = grupaRobeRepository.findByPdvId(pdvId);
        for (GrupaRobe grupaRobe : listaGrupeRobe) {
            izbrisiGrupuRobe(grupaRobe);
        }
    }
}
