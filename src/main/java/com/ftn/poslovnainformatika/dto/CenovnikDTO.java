package com.ftn.poslovnainformatika.dto;

import com.ftn.poslovnainformatika.model.PoslovniPartner;
import com.ftn.poslovnainformatika.model.Preduzece;
import com.ftn.poslovnainformatika.model.StavkeCenovnika;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CenovnikDTO {

    private Long Id;

    private Date pocetakRokaTrajanja;

    private Date krajRokaTrajanja;

    private PoslovniPartner poslovniPartner;

    private Preduzece preduzece;

    private Set<StavkeCenovnika> cene = new HashSet<StavkeCenovnika>();

    private boolean obrisano;
}
