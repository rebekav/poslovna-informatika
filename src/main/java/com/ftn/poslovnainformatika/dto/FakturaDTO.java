package com.ftn.poslovnainformatika.dto;

import com.ftn.poslovnainformatika.model.*;
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
public class FakturaDTO {

    private Long id;

    private long brojFakture;

    private Date datumFakture;

    private Date datumValute;

    private double osnovica;

    private double ukupanPdv;

    private double rabat;

    private double iznosBezRabata;

    private double iznosZaPlacanje;

    private boolean placeno;

    private boolean vrstaFakture;

    private PoslovnaGodina poslovnaGodina;

    private PoslovniPartner poslovniPartner;

    private Preduzece preduzece;

    private Narudzbenica narudzbenica;

    private Otpremnica otpremnica;

    private Set<StavkaFakture> stavkeFakture = new HashSet<>();
}
