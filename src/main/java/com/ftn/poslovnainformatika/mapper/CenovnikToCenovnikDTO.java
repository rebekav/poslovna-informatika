package com.ftn.poslovnainformatika.mapper;


import com.ftn.poslovnainformatika.dto.CenovnikDTO;
import com.ftn.poslovnainformatika.model.Cenovnik;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CenovnikToCenovnikDTO {

    @Autowired
    private ModelMapper modelMapper;

    public CenovnikDTO konvertujEntityToDto(Cenovnik cenovnik) {

        return modelMapper.map(cenovnik, CenovnikDTO.class);
    }

    public List<CenovnikDTO> konvertujEntityToDto(List<Cenovnik> cenovnici) {
        List<CenovnikDTO> listaCenovnika = new ArrayList<CenovnikDTO>();
        for (Cenovnik cenovnik : cenovnici) {
            listaCenovnika.add(konvertujEntityToDto(cenovnik));
        }
        return listaCenovnika;
    }

}