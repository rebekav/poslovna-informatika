package com.ftn.poslovnainformatika.dto;


import com.ftn.poslovnainformatika.model.GrupaRobe;
import com.ftn.poslovnainformatika.model.StavkaFakture;
import com.ftn.poslovnainformatika.model.StavkeCenovnika;
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
public class RobaDTO {

    private long id;

    private String nazivRobe;

    private String jedinicaMere;

    private GrupaRobe grupaRobe;

    private Set<StavkeCenovnika> stavkeCenovnika = new HashSet<>();

    private Set<StavkaFakture> stavkeFakture = new HashSet<>();

    private boolean obrisano;

}
