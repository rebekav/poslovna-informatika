package com.ftn.poslovnainformatika.service;

import com.ftn.poslovnainformatika.model.GrupaRobe;
import java.util.List;

public interface IGrupaRobeService {

    List<GrupaRobe> findAll();

    GrupaRobe findOne(Long id);

    void izbrisiGrupuRobe(GrupaRobe grupaRobe);

    void save(GrupaRobe grupaRobe);

}
