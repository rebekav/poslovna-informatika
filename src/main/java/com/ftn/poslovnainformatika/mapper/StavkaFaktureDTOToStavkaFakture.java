package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.StavkaFaktureDTO;
import com.ftn.poslovnainformatika.model.StavkaFakture;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StavkaFaktureDTOToStavkaFakture {

    @Autowired
    private ModelMapper modelMapper;

    public StavkaFakture konvertujDtoToEntity(StavkaFaktureDTO stavkaFaktureDTO) {

        StavkaFakture stavkaFakture = modelMapper.map(stavkaFaktureDTO, StavkaFakture.class);
        return stavkaFakture;
    }
}