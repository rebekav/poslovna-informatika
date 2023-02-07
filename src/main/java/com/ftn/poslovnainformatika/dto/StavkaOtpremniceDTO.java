package com.ftn.poslovnainformatika.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StavkaOtpremniceDTO {

    private long id;

    private String jedinicaMere;

    private int kolicina;

    private String opis;

    private float cena;

    private float ukupanIznos;

    private long otpremnica;

    private long roba;

    private boolean obrisano;
}
