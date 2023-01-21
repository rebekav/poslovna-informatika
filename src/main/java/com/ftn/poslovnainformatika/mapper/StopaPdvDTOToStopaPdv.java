package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.StopaPdvDTO;
import com.ftn.poslovnainformatika.model.StopaPdv;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StopaPdvDTOToStopaPdv {

    @Autowired
    private ModelMapper modelMapper;

    public StopaPdv konvertujDtoToEntity(StopaPdvDTO stopaPdvDTO) {

        StopaPdv stopaPdv = modelMapper.map(stopaPdvDTO, StopaPdv.class);
        return stopaPdv;
    }

}
