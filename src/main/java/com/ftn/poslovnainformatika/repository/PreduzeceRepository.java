package com.ftn.poslovnainformatika.repository;

import com.ftn.poslovnainformatika.model.Preduzece;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreduzeceRepository extends JpaRepository<Preduzece, Long> {

}
