package com.ftn.poslovnainformatika.repository;


import com.ftn.poslovnainformatika.model.Roba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RobaRepository extends JpaRepository<Roba, Long> {

}
