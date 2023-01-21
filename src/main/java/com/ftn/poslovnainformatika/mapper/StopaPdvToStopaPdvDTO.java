package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.StopaPdvDTO;
import com.ftn.poslovnainformatika.model.StopaPdv;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StopaPdvToStopaPdvDTO {

    @Autowired
    private ModelMapper modelMapper;

    public StopaPdvDTO konvertujEntityToDto(StopaPdv stopaPdv) {

        return modelMapper.map(stopaPdv, StopaPdvDTO.class);
    }

    public List<StopaPdvDTO> konvertujEntityToDto(List<StopaPdv> stopaPdvLista) {
        List<StopaPdvDTO> stopePdv = new ArrayList<StopaPdvDTO>();
        for (StopaPdv stopaPdv : stopaPdvLista) {
            stopePdv.add(konvertujEntityToDto(stopaPdv));
        }
        return stopePdv;
    }

}
