package com.ftn.poslovnainformatika.mapper;


import com.ftn.poslovnainformatika.dto.StavkaCenovnikaDTO;
import com.ftn.poslovnainformatika.model.StavkeCenovnika;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StavkaCenovnikaDTOToStavkaCenovnika {

    @Autowired
    private ModelMapper modelMapper;

    public StavkeCenovnika konvertujDtoToEntity(StavkaCenovnikaDTO stavkaCenovnikaDTO) {

        StavkeCenovnika stavkaCenovnika = modelMapper.map(stavkaCenovnikaDTO, StavkeCenovnika.class);
        return stavkaCenovnika;
    }
}