package com.ftn.poslovnainformatika.dto;


import com.ftn.poslovnainformatika.model.Mesto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PreduzeceDTO {

    private Long id;

    private String nazivPreduzeca;

    private String PIB;

    private String tekuciRacun;

    private String emailAdresa;

    private String adresa;

    private String telefon;

    private Mesto mesto;

}
