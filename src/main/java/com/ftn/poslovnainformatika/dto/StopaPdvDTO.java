package com.ftn.poslovnainformatika.dto;

import com.ftn.poslovnainformatika.model.GrupaRobe;
import com.ftn.poslovnainformatika.model.Pdv;
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
public class StopaPdvDTO {

    public long id;

    public double procenat;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date rokVazenja;

    private Pdv pdv;

    private Set<GrupaRobe> grupeRobe = new HashSet<>();

    private boolean obrisano;

}
