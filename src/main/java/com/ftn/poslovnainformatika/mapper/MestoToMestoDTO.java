package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.MestoDTO;
import com.ftn.poslovnainformatika.model.Mesto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MestoToMestoDTO {

    @Autowired
    private ModelMapper modelMapper;

    public MestoDTO konvertujEntityToDto(Mesto mesto) {

        return modelMapper.map(mesto, MestoDTO.class);
    }

    public List<MestoDTO> konvertujEntityToDto(List<Mesto> mesta) {
        List<MestoDTO> listaMesta = new ArrayList<MestoDTO>();
        for (Mesto mesto : mesta) {
            listaMesta.add(konvertujEntityToDto(mesto));
        }
        return listaMesta;
    }

}
