package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.model.Mesto;
import com.ftn.poslovnainformatika.repository.MestoRepository;
import com.ftn.poslovnainformatika.service.IMestoService;
import com.ftn.poslovnainformatika.service.IPoslovniPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MestoService implements IMestoService {

    @Autowired
    MestoRepository mestoRepository;

    @Autowired
    IPoslovniPartnerService poslovniPartnerService;

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

    @Override
    public void save(Mesto mesto) {
        mestoRepository.save(mesto);

    }

    @Override
    public void izbrisiMesto(Mesto mesto) {
        mesto.setObrisano(true);
        poslovniPartnerService.izbrisiPoslovnogPartneraByMestoId(mesto.getId());
        mestoRepository.save(mesto);
    }
}
