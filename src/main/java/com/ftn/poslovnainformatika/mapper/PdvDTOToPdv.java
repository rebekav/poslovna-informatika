package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.PdvDTO;
import com.ftn.poslovnainformatika.model.Pdv;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PdvDTOToPdv {

    @Autowired
    private ModelMapper modelMapper;

    public Pdv konvertujDtoToEntity(PdvDTO pdvDTO) {

        Pdv pdv = modelMapper.map(pdvDTO, Pdv.class);
        return pdv;
    }

}
