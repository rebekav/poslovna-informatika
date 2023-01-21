package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.PreduzeceDTO;
import com.ftn.poslovnainformatika.model.Preduzece;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PreduzeceDTOToPreduzece {


    @Autowired
    private ModelMapper modelMapper;

    public Preduzece konvertujDtoToEntity(PreduzeceDTO preduzeceDTO) {
        Preduzece preduzece = modelMapper.map(preduzeceDTO, Preduzece.class);
        return preduzece;
    }
}
