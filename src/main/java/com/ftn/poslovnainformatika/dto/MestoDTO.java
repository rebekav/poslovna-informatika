package com.ftn.poslovnainformatika.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MestoDTO {

    private Long id;

    private String grad;

    private Long postanskiBroj;

    private boolean obrisano;
}