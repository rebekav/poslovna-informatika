package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.model.Cenovnik;
import com.ftn.poslovnainformatika.repository.CenovnikRepository;
import com.ftn.poslovnainformatika.service.ICenovnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CenovnikService implements ICenovnikService {

    @Autowired
    CenovnikRepository cenovnikRepository;

    @Override
    public List<Cenovnik> findAll() {
        return cenovnikRepository.findAll();
    }

    @Override
    public Cenovnik findOne(long id) {
        return cenovnikRepository.getOne(id);
    }

    @Override
    public void save(Cenovnik cenovnik) {
        cenovnikRepository.save(cenovnik);

    }

}