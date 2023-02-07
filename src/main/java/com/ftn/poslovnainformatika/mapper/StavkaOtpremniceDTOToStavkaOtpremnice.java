package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.StavkaOtpremniceDTO;
import com.ftn.poslovnainformatika.model.StavkaOtpremnice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StavkaOtpremniceDTOToStavkaOtpremnice {

    @Autowired
    private ModelMapper modelMapper;

    public StavkaOtpremnice konvertujDtoToEntity(StavkaOtpremniceDTO stavkaOtpremniceDTO) {

        StavkaOtpremnice stavkaOtpremnice = modelMapper.map(stavkaOtpremniceDTO, StavkaOtpremnice.class);
        return stavkaOtpremnice;
    }

}
