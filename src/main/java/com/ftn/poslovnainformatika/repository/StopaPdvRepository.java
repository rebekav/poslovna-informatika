package com.ftn.poslovnainformatika.repository;



import com.ftn.poslovnainformatika.model.StopaPdv;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StopaPdvRepository extends JpaRepository<StopaPdv, Long>{

}
