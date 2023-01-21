package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.MestoDTO;
import com.ftn.poslovnainformatika.model.Mesto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MestoDTOToMesto {

    @Autowired
    private ModelMapper modelMapper;

    public Mesto konvertujDtoToEntity(MestoDTO mestoDTO) {

        Mesto mesto = modelMapper.map(mestoDTO, Mesto.class);
        return mesto;
    }

}
