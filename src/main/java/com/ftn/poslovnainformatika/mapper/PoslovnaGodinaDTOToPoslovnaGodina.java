package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.PoslovnaGodinaDTO;
import com.ftn.poslovnainformatika.model.PoslovnaGodina;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PoslovnaGodinaDTOToPoslovnaGodina {

    @Autowired
    private ModelMapper modelMapper;

    public PoslovnaGodina konvertujDtoToEntity(PoslovnaGodinaDTO poslovnaGodinaDTO) {

        PoslovnaGodina poslovnaGodina = modelMapper.map(poslovnaGodinaDTO, PoslovnaGodina.class);
        return poslovnaGodina;
    }

}
