package com.ftn.poslovnainformatika.repository;

import com.ftn.poslovnainformatika.model.PoslovnaGodina;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoslovnaGodinaRepository extends JpaRepository<PoslovnaGodina, Long> {

    List<PoslovnaGodina> findByZakljucenaGodinaIsFalseAndObrisanoIsFalse();

}
