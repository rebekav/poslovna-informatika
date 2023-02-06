package com.ftn.poslovnainformatika.service;

import com.ftn.poslovnainformatika.model.Cenovnik;

import java.util.List;

public interface ICenovnikService {

    List<Cenovnik> findAll();
    Cenovnik findOne(long id);
    void save(Cenovnik cenovnik);
}
