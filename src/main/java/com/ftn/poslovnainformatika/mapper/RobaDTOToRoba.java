package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.RobaDTO;
import com.ftn.poslovnainformatika.model.Roba;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RobaDTOToRoba {

    @Autowired
    private ModelMapper modelMapper;

    public Roba konvertujDtoToEntity(RobaDTO robaDTO) {

        Roba roba = modelMapper.map(robaDTO, Roba.class);
        return roba;
    }

}
