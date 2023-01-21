package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.GrupaRobeDTO;
import com.ftn.poslovnainformatika.model.GrupaRobe;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GrupaRobeToStopaGrupaRobeDTO {

    @Autowired
    private ModelMapper modelMapper;

    public GrupaRobeDTO konvertujEntityToDto(GrupaRobe grupaRobe) {

        return modelMapper.map(grupaRobe, GrupaRobeDTO.class);
    }

    public List<GrupaRobeDTO> konvertujEntityToDto(List<GrupaRobe> grupaRobeLista) {
        List<GrupaRobeDTO> grupeRoba = new ArrayList<GrupaRobeDTO>();
        for (GrupaRobe grupaRobe : grupaRobeLista) {
            grupeRoba.add(konvertujEntityToDto(grupaRobe));
        }
        return grupeRoba;
    }

}
