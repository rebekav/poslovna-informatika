package com.ftn.poslovnainformatika.dto;

import com.ftn.poslovnainformatika.model.GrupaRobe;
import com.ftn.poslovnainformatika.model.StopaPdv;
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
public class PdvDTO {

    private long id;

    private String vrstaPdv;

    private Set<StopaPdv> stopePdv = new HashSet<>();

    private Set<GrupaRobe> grupaRobe = new HashSet<>();

    private boolean obrisano;

}
