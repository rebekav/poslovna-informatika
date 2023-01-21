package com.ftn.poslovnainformatika.repository;

import com.ftn.poslovnainformatika.model.StavkaOtpremnice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StavkaOtpremniceRepository extends JpaRepository<StavkaOtpremnice, Long> {

}
