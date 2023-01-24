package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.RobaDTO;
import com.ftn.poslovnainformatika.model.Roba;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RobaToRobaDTO {

    @Autowired
    private ModelMapper modelMapper;

    public RobaDTO konvertujEntityToDto(Roba roba) {

        return modelMapper.map(roba, RobaDTO.class);
    }

    public List<RobaDTO> konvertujEntityToDto(List<Roba> robaLista) {
        List<RobaDTO> listaRobe = new ArrayList<RobaDTO>();
        for (Roba roba : robaLista) {
            listaRobe.add(konvertujEntityToDto(roba));
        }
        return listaRobe;
    }

}