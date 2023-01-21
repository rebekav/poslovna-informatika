package com.ftn.poslovnainformatika.repository;

import com.ftn.poslovnainformatika.model.Narudzbenica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NarudzbenicaRepository extends JpaRepository<Narudzbenica, Long> {

}
