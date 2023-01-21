package com.ftn.poslovnainformatika.service;

import com.ftn.poslovnainformatika.model.StopaPdv;
import java.util.List;

public interface IStopaPdvService {

    List<StopaPdv> findAll();

    StopaPdv findOne(Long id);

    void save(StopaPdv stopaPDV);

    void izbrisiStopuPdv(StopaPdv stopaPDV);

}
