package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.StavkaCenovnikaDTO;
import com.ftn.poslovnainformatika.model.StavkeCenovnika;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StavkaCenovnikaToStavkaCenovnikaDTO {

    @Autowired
    private ModelMapper modelMapper;

    public StavkaCenovnikaDTO konvertujEntityToDto(StavkeCenovnika stavkaCenovnika) {

        return modelMapper.map(stavkaCenovnika, StavkaCenovnikaDTO.class);
    }

    public List<StavkaCenovnikaDTO> konvertujEntityToDto(List<StavkeCenovnika> stavkeCenovnika) {
        List<StavkaCenovnikaDTO> listaStavki = new ArrayList<StavkaCenovnikaDTO>();
        for (StavkeCenovnika stavkaCenovnika : stavkeCenovnika) {
            listaStavki.add(konvertujEntityToDto(stavkaCenovnika));
        }
        return listaStavki;
    }
}
