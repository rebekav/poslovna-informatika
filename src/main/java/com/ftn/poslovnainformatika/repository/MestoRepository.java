package com.ftn.poslovnainformatika.repository;

import com.ftn.poslovnainformatika.model.Mesto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MestoRepository extends JpaRepository<Mesto, Long> {

}
