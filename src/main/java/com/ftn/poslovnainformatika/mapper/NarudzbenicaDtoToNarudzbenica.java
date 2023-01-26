package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.NarudzbenicaDTO;
import com.ftn.poslovnainformatika.model.Narudzbenica;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NarudzbenicaDtoToNarudzbenica {

    @Autowired
    private ModelMapper modelMapper;

    public Narudzbenica konvertujDtoToEntity(NarudzbenicaDTO narudzbenicaDTO) {

        Narudzbenica narudzbenica = modelMapper.map(narudzbenicaDTO, Narudzbenica.class);
        return narudzbenica;
    }

}
