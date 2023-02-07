package com.ftn.poslovnainformatika.repository;

import com.ftn.poslovnainformatika.model.Narudzbenica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.ftn.poslovnainformatika.model.StavkaNarudzbenice;

import java.util.List;

@Repository
public interface NarudzbenicaRepository extends JpaRepository<Narudzbenica, Long> {

    @Query("select e from Narudzbenica e where e.obrisano=false")
    List<Narudzbenica> findAllNarudzbenice();
}