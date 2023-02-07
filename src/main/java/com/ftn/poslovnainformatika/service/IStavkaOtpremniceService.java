package com.ftn.poslovnainformatika.service;

import com.ftn.poslovnainformatika.model.StavkaOtpremnice;

import java.util.List;

public interface IStavkaOtpremniceService {

    List<StavkaOtpremnice> findAll();

    void save(StavkaOtpremnice stavka);

    StavkaOtpremnice findOne(long id);

    void izbrisiStavkuOtpremnice(StavkaOtpremnice stavka);

}
