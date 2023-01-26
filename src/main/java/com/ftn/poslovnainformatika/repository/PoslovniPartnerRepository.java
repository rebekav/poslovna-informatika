package com.ftn.poslovnainformatika.repository;

import com.ftn.poslovnainformatika.model.PoslovniPartner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoslovniPartnerRepository extends JpaRepository<PoslovniPartner, Long>{

    List<PoslovniPartner> findByMestoId(Long mestoId);

}
