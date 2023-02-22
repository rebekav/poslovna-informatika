package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.model.Preduzece;
import com.ftn.poslovnainformatika.repository.PreduzeceRepository;
import com.ftn.poslovnainformatika.service.IPreduzeceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PreduzeceService implements IPreduzeceService {


    @Autowired
    PreduzeceRepository preduzeceRepository;

    @Override
    public List<Preduzece> findAll() {
        return preduzeceRepository.findAll();
    }

    @Override
    public Preduzece findOne(Long id) {
        return preduzeceRepository.getOne(id);
    }

    @Override
    public void save(Preduzece preduzece) {

        preduzeceRepository.save(preduzece);

    }


}
