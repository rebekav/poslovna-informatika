package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.PreduzeceDTO;
import com.ftn.poslovnainformatika.model.Preduzece;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PreduzeceToPreduzeceDTO {

    @Autowired
    private ModelMapper modelMapper;

    public PreduzeceDTO konvertujEntityToDto(Preduzece preduzece) {

        return modelMapper.map(preduzece, PreduzeceDTO.class);
    }

    public List<PreduzeceDTO> konvertujEntityToDto(List<Preduzece> preduzeca) {
        List<PreduzeceDTO> listaPreduzeca = new ArrayList<PreduzeceDTO>();
        for (Preduzece preduzece : preduzeca) {
            listaPreduzeca.add(konvertujEntityToDto(preduzece));
        }
        return listaPreduzeca;
    }
}
