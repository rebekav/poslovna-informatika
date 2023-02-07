package com.ftn.poslovnainformatika.repository;

import com.ftn.poslovnainformatika.model.StavkaNarudzbenice;
import com.ftn.poslovnainformatika.model.StavkaOtpremnice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StavkaNarudzbeniceRepository extends JpaRepository<StavkaNarudzbenice, Long> {

    @Query("FROM StavkaNarudzbenice sn where sn.narudzbenica = :narudzbenica")
    List<StavkaNarudzbenice> findAllByNarudzbenicaId(@Param("narudzbenica") Long narudzbenica);

    @Query("select e from StavkaNarudzbenice e where e.obrisano=false")
    List<StavkaNarudzbenice> findAllStavkeNarudzbenice();

    @Query("select e from StavkaOtpremnice e where e.obrisano=false")
    List<StavkaOtpremnice> findAllOtpremnice();

}
