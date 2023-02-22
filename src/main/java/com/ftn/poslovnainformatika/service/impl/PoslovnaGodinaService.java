package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.model.PoslovnaGodina;
import com.ftn.poslovnainformatika.repository.PoslovnaGodinaRepository;
import com.ftn.poslovnainformatika.service.IPoslovnaGodinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional(readOnly = true)
public class PoslovnaGodinaService implements IPoslovnaGodinaService {

    @Autowired
    PoslovnaGodinaRepository poslovnaGodinaRepository;

    @Override
    public List<PoslovnaGodina> findAll() {
        return poslovnaGodinaRepository.findAll();
    }

    @Override
    public PoslovnaGodina getOne(long id) {
        return poslovnaGodinaRepository.getOne(id);
    }

    @Override
    public List<PoslovnaGodina> findByZakljucenaGodinaIsFalseAndObrisanoIsFalse() {
        return poslovnaGodinaRepository.findByZakljucenaGodinaIsFalseAndObrisanoIsFalse();
    }

}
