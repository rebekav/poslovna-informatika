package com.ftn.poslovnainformatika.repository;

import com.ftn.poslovnainformatika.model.StavkeCenovnika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StavkaCenovnikaRepository extends JpaRepository<StavkeCenovnika, Long>{

}
