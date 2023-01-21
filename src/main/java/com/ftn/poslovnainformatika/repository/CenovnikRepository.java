package com.ftn.poslovnainformatika.repository;

import com.ftn.poslovnainformatika.model.Cenovnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CenovnikRepository extends JpaRepository<Cenovnik, Long> {

}
