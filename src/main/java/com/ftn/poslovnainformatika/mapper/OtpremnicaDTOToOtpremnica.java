package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.OtpremnicaDTO;
import com.ftn.poslovnainformatika.model.Otpremnica;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OtpremnicaDTOToOtpremnica {
    @Autowired
    private ModelMapper modelMapper;

    public Otpremnica konvertujDtoToEntity(OtpremnicaDTO otpremnicaDTO) {

        Otpremnica otpremnica = modelMapper.map(otpremnicaDTO, Otpremnica.class);
        return otpremnica;
    }
}