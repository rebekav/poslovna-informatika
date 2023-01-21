package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.GrupaRobeDTO;
import com.ftn.poslovnainformatika.model.GrupaRobe;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GrupaRobeDTOToGrupaRobe {

    @Autowired
    private ModelMapper modelMapper;

    public GrupaRobe konvertujDtoToEntity(GrupaRobeDTO grupaRobeDTO) {

        GrupaRobe grupaRobe = modelMapper.map(grupaRobeDTO, GrupaRobe.class);
        return grupaRobe;
    }

}
