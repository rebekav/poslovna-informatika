package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.FakturaDTO;
import com.ftn.poslovnainformatika.model.Faktura;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FakturaDTOToFaktura {

    @Autowired
    private ModelMapper modelMapper;

    public Faktura konvertujDtoToEntity(FakturaDTO fakturaDTO) {

        Faktura faktura = modelMapper.map(fakturaDTO, Faktura.class);
        return faktura;
    }
}
