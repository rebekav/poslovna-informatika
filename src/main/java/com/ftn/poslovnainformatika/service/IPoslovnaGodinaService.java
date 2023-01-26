package com.ftn.poslovnainformatika.service;

import com.ftn.poslovnainformatika.model.PoslovnaGodina;

import java.util.List;

public interface IPoslovnaGodinaService {

    List<PoslovnaGodina> findAll();
    PoslovnaGodina getOne(long id);
    List<PoslovnaGodina> findByZakljucenaGodinaIsFalseAndObrisanoIsFalse();

}
