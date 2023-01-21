package com.ftn.poslovnainformatika.dto;

import com.ftn.poslovnainformatika.model.Pdv;
import com.ftn.poslovnainformatika.model.Preduzece;
import com.ftn.poslovnainformatika.model.StopaPdv;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GrupaRobeDTO {

    private long id;

    private String naziv;

    private Preduzece preduzece;

    private Pdv pdv;

    private StopaPdv stopapdv;

    private boolean obrisano;

}