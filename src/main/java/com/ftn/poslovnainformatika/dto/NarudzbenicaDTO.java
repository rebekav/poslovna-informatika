package com.ftn.poslovnainformatika.dto;

import com.ftn.poslovnainformatika.model.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NarudzbenicaDTO {

    private long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datumNarudzbenice;

    private long brojNarudzbenice;

    private Narudzbenica.TipNarudzbenice tipNarudzbenice;

    private PoslovnaGodina poslovnaGodina;

    private PoslovniPartner poslovniPartner;

    private Preduzece preduzece;

    private Set<StavkaNarudzbenice> stavkeNarudzbenice = new HashSet<>();

    private Set<Otpremnica> otpremnice = new HashSet<>();

    private Set<Faktura> fakture = new HashSet<>();

    private boolean obrisano;
}
