package com.ftn.poslovnainformatika.repository;

import com.ftn.poslovnainformatika.model.GrupaRobe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupaRobeRepository extends JpaRepository<GrupaRobe, Long>{

    List<GrupaRobe> findByStopaPdvId(Long stopaPdvId);

}
