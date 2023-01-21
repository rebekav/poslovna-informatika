package com.ftn.poslovnainformatika.service;

import com.ftn.poslovnainformatika.model.Pdv;
import java.util.List;

public interface IPdvService {

    Pdv findOne(Long id);

    List<Pdv> findAll();

}
