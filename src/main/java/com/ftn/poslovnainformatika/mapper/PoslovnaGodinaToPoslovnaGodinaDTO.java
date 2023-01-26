package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.PoslovnaGodinaDTO;
import com.ftn.poslovnainformatika.model.PoslovnaGodina;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PoslovnaGodinaToPoslovnaGodinaDTO {

    @Autowired
    private ModelMapper modelMapper;

    public PoslovnaGodinaDTO konvertujEntityToDto(PoslovnaGodina poslovnaGodina) {

        return modelMapper.map(poslovnaGodina, PoslovnaGodinaDTO.class);
    }

    public List<PoslovnaGodinaDTO> konvertujEntityToDto(List<PoslovnaGodina> poslovneGodine) {
        List<PoslovnaGodinaDTO> listaPoslovnihGodina = new ArrayList<>();
        for (PoslovnaGodina poslovnaGodina : poslovneGodine) {
            listaPoslovnihGodina.add(konvertujEntityToDto(poslovnaGodina));
        }
        return listaPoslovnihGodina;
    }

}
