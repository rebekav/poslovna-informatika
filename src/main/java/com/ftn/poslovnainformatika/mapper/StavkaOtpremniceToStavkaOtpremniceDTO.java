package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.StavkaOtpremniceDTO;
import com.ftn.poslovnainformatika.model.StavkaOtpremnice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StavkaOtpremniceToStavkaOtpremniceDTO {

    @Autowired
    private ModelMapper modelMapper;

    public StavkaOtpremniceDTO konvertujEntityToDto(StavkaOtpremnice stavkaOtpremnice) {

        return modelMapper.map(stavkaOtpremnice, StavkaOtpremniceDTO.class);
    }

    public List<StavkaOtpremniceDTO> konvertujEntityToDto(List<StavkaOtpremnice> stavkeOtpremnice) {
        List<StavkaOtpremniceDTO> listaStavki = new ArrayList<StavkaOtpremniceDTO>();
        for (StavkaOtpremnice stavkaOtpremnice : stavkeOtpremnice) {
            listaStavki.add(konvertujEntityToDto(stavkaOtpremnice));
        }
        return listaStavki;
    }
}
