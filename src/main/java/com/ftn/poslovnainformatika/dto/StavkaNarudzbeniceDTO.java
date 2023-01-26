package com.ftn.poslovnainformatika.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StavkaNarudzbeniceDTO {

    private long id;

    private String jedinicaMere;

    private int kolicina;

    private String opis;

    private long narudzbenica;

    private long roba;

    private boolean obrisano;
}
