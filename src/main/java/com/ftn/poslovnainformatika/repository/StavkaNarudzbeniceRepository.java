package com.ftn.poslovnainformatika.repository;

import com.ftn.poslovnainformatika.model.StavkaNarudzbenice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StavkaNarudzbeniceRepository extends JpaRepository<StavkaNarudzbenice, Long> {

}
