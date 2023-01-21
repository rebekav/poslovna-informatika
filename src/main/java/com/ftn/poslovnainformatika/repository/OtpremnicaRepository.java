package com.ftn.poslovnainformatika.repository;

import com.ftn.poslovnainformatika.model.Otpremnica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtpremnicaRepository extends JpaRepository<Otpremnica, Long> {

}
