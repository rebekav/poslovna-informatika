package com.ftn.poslovnainformatika.dto;

import com.ftn.poslovnainformatika.model.Faktura;
import com.ftn.poslovnainformatika.model.Narudzbenica;
import com.ftn.poslovnainformatika.model.Otpremnica;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PoslovnaGodinaDTO {

    private long id;

    private int godinaPoslovanja;

    private boolean zakljucenaGodina;

    private Set<Narudzbenica> narudzbenice = new HashSet<>();

    private Set<Otpremnica> otpremnice = new HashSet<>();

    private Set<Faktura> fakture = new HashSet<>();

    private boolean obrisano;
}
