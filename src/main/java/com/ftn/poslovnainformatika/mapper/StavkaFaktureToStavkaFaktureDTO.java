package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.StavkaFaktureDTO;
import com.ftn.poslovnainformatika.model.StavkaFakture;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StavkaFaktureToStavkaFaktureDTO {

    @Autowired
    private ModelMapper modelMapper;

    public StavkaFaktureDTO konvertujEntityToDto(StavkaFakture stavkaFakture) {

        return modelMapper.map(stavkaFakture, StavkaFaktureDTO.class);
    }

    public List<StavkaFaktureDTO> konvertujEntityToDto(List<StavkaFakture> stavkeFakture) {
        List<StavkaFaktureDTO> stavkeFakturaList = new ArrayList<StavkaFaktureDTO>();
        for (StavkaFakture stavkaFakture : stavkeFakture) {
            stavkeFakturaList.add(konvertujEntityToDto(stavkaFakture));
        }
        return stavkeFakturaList;
    }
}
