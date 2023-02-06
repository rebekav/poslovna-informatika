package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.CenovnikDTO;
import com.ftn.poslovnainformatika.model.Cenovnik;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CenovnikDTOCenovnik {

    @Autowired
    private ModelMapper modelMapper;

    public Cenovnik konvertujDtoToEntity(CenovnikDTO cenovnikDTO) {

        Cenovnik cenovnik = modelMapper.map(cenovnikDTO, Cenovnik.class);
        return cenovnik;
    }
}
