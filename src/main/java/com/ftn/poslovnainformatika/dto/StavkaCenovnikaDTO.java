package com.ftn.poslovnainformatika.dto;

import com.ftn.poslovnainformatika.model.Cenovnik;
import com.ftn.poslovnainformatika.model.Roba;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StavkaCenovnikaDTO {

    private long id;

    private float cena;

    private Cenovnik cenovnik;

    private Roba roba;

    private boolean obrisano;
}
