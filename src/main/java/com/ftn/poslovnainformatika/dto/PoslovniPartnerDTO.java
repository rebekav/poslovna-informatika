package com.ftn.poslovnainformatika.dto;

import com.ftn.poslovnainformatika.model.Mesto;
import com.ftn.poslovnainformatika.model.PoslovniPartner.TipPoslovnogPartnera;
import com.ftn.poslovnainformatika.model.Preduzece;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PoslovniPartnerDTO {

    private Long id;

    private String PIB;

    private String nazivPoslovnogPartnera;

    private String adresa;

    private boolean obrisano;

    private String tekuciRacun;

    private TipPoslovnogPartnera tipPoslovnogPartnera;

    private Mesto mesto;

    private Preduzece preduzece;
}
