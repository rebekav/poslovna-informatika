package com.ftn.poslovnainformatika.repository;

import com.ftn.poslovnainformatika.model.StavkaOtpremnice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StavkaOtpremniceRepository extends JpaRepository<StavkaOtpremnice, Long> {

    @Query("select e from StavkaOtpremnice e where e.obrisano=false")
    List<StavkaOtpremnice> findAllOtpremnice();

}
