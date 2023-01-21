package com.ftn.poslovnainformatika.service;

import com.ftn.poslovnainformatika.model.Preduzece;

import java.util.List;

public interface IPreduzeceService {

    List<Preduzece> findAll();

    Preduzece findOne(Long id);

    void save(Preduzece preduzece);
}
