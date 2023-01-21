package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.model.PoslovniPartner;
import com.ftn.poslovnainformatika.repository.PoslovniPartnerRepository;
import com.ftn.poslovnainformatika.service.IPoslovniPartnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PoslovniPartnerService implements IPoslovniPartnerService {

    @Autowired
    PoslovniPartnerRepository poslovniPartnerRepository;

    @Override
    public List<PoslovniPartner> findAll() {
        return poslovniPartnerRepository.findAll();
    }

    @Override
    public PoslovniPartner findOne(Long id) {
        return poslovniPartnerRepository.getOne(id);
    }

    @Override
    public void save(PoslovniPartner poslovniPartner) {
        poslovniPartnerRepository.save(poslovniPartner);
    }

}