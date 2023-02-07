package com.ftn.poslovnainformatika.repository;

import com.ftn.poslovnainformatika.model.Otpremnica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OtpremnicaRepository extends JpaRepository<Otpremnica, Long> {

    @Query("select e from Otpremnica e where e.obrisano=false")
    List<Otpremnica> findAllOtpremnice();
}


