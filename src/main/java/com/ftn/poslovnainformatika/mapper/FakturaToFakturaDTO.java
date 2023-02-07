package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.FakturaDTO;
import com.ftn.poslovnainformatika.model.Faktura;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class FakturaToFakturaDTO {

    @Autowired
    private ModelMapper modelMapper;

    public FakturaDTO konvertujEntityToDto(Faktura faktura) {

        return modelMapper.map(faktura, FakturaDTO.class);
    }

    public List<FakturaDTO> konvertujEntityToDto(List<Faktura> fakture) {
        List<FakturaDTO> listaFaktura = new ArrayList<FakturaDTO>();
        for (Faktura faktura : fakture) {
            listaFaktura.add(konvertujEntityToDto(faktura));
        }
        return listaFaktura;
    }
}
