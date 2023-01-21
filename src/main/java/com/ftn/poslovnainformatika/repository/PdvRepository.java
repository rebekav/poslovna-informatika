package com.ftn.poslovnainformatika.repository;

import com.ftn.poslovnainformatika.model.Pdv;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PdvRepository extends JpaRepository<Pdv, Long> {

}
