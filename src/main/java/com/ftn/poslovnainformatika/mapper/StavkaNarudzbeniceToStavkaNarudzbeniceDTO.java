package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.StavkaNarudzbeniceDTO;
import com.ftn.poslovnainformatika.model.StavkaNarudzbenice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StavkaNarudzbeniceToStavkaNarudzbeniceDTO {

    @Autowired
    private ModelMapper modelMapper;

    public StavkaNarudzbeniceDTO konvertujEntityToDto(StavkaNarudzbenice stavkaNarudzbenice) {

        return modelMapper.map(stavkaNarudzbenice, StavkaNarudzbeniceDTO.class);
    }

    public List<StavkaNarudzbeniceDTO> konvertujEntityToDto(List<StavkaNarudzbenice> stavkeNarudzbenice) {
        List<StavkaNarudzbeniceDTO> listaStavki = new ArrayList<>();
        for (StavkaNarudzbenice stavkeNarudzbenica : stavkeNarudzbenice) {
            listaStavki.add(konvertujEntityToDto(stavkeNarudzbenica));
        }
        return listaStavki;
    }

}
