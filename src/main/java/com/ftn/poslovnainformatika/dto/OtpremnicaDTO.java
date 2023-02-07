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
public class OtpremnicaDTO {

    private long id;

    private long brojOtpremnice;

    private Date datumOtpremnice;

    private boolean tipOtpremnice;

    private double racunOtpremnice;


    private PoslovnaGodina poslovnaGodina;


    private PoslovniPartner poslovniPartner;


    private Preduzece preduzece;


    private Narudzbenica narudzbenica;


    private Set<StavkaOtpremnice> stavkeOtpremnice = new HashSet<>();

    private Set<Faktura> fakture = new HashSet<>();

    private boolean obrisano;
}