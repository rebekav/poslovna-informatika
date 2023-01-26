package com.ftn.poslovnainformatika.repository;

import com.ftn.poslovnainformatika.model.StavkaNarudzbenice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StavkaNarudzbeniceRepository extends JpaRepository<StavkaNarudzbenice, Long> {

    @Query("FROM StavkaNarudzbenice sn where sn.narudzbenica = :narudzbenica")
    List<StavkaNarudzbenice> findAllByNarudzbenicaId(@Param("narudzbenica") Long narudzbenica);

}
