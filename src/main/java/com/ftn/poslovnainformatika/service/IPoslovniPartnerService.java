package com.ftn.poslovnainformatika.service;

import com.ftn.poslovnainformatika.model.PoslovniPartner;

import java.util.List;

public interface IPoslovniPartnerService {

    List<PoslovniPartner> findAll();

    PoslovniPartner findOne(Long id);

    void save(PoslovniPartner poslovniPartner);

    void izbrisiPoslovnogPartneraByMestoId (Long mestoId);

    void izbrisiPoslovnogPartnera(PoslovniPartner poslovniPartner);

}
