package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.PdvDTO;
import com.ftn.poslovnainformatika.model.Pdv;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PdvToPdvDTO {

    @Autowired
    private ModelMapper modelMapper;

    public PdvDTO konvertujEntityToDto(Pdv pdv) {

        return modelMapper.map(pdv, PdvDTO.class);
    }

    public List<PdvDTO> konvertujEntityToDto(List<Pdv> pdvLista) {
        List<PdvDTO> listaPdv = new ArrayList<PdvDTO>();
        for (Pdv pdv : pdvLista) {
            listaPdv.add(konvertujEntityToDto(pdv));
        }
        return listaPdv;
    }

}
