package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.NarudzbenicaDTO;
import com.ftn.poslovnainformatika.model.Narudzbenica;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NarudzbenicaToNarudzbenicaDTO {

    @Autowired
    private ModelMapper modelMapper;

    public NarudzbenicaDTO konvertujEntityToDto(Narudzbenica narudzbenica) {

        return modelMapper.map(narudzbenica, NarudzbenicaDTO.class);
    }

    public List<NarudzbenicaDTO> konvertujEntityToDto(List<Narudzbenica> narudzbenice) {
        List<NarudzbenicaDTO> listaNarudzbenica = new ArrayList<>();
        for (Narudzbenica narudzbenica : narudzbenice) {
            listaNarudzbenica.add(konvertujEntityToDto(narudzbenica));
        }
        return listaNarudzbenica;
    }

}
