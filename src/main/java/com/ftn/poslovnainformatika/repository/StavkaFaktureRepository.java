package com.ftn.poslovnainformatika.repository;


import com.ftn.poslovnainformatika.model.StavkaFakture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StavkaFaktureRepository extends JpaRepository<StavkaFakture, Long>{

}
