package com.ftn.poslovnainformatika.repository;


import com.ftn.poslovnainformatika.model.Roba;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RobaRepository extends JpaRepository<Roba, Long> {

    public List<Roba> findByGrupaRobeId(Long grupaRobeId);

}
