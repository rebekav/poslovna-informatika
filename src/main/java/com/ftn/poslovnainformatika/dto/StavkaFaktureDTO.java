package com.ftn.poslovnainformatika.dto;

import com.ftn.poslovnainformatika.model.Faktura;
import com.ftn.poslovnainformatika.model.Roba;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StavkaFaktureDTO {

    private long id;

    private double cena;

    private long kolicina;

    private double osnovicaPDV;

    private double procenatPDV;

    private double iznosPDV;

    private double rabat;

    private double ukupanIznos;

    private Roba roba;

    private Faktura faktura;

    private boolean obrisano;
}