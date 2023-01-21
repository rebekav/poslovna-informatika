package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.PoslovniPartnerDTO;
import com.ftn.poslovnainformatika.model.PoslovniPartner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PoslovniPartnerToPoslovniPartnerDTO {

    @Autowired
    private ModelMapper modelMapper;

    public PoslovniPartnerDTO konvertujEntityToDto(PoslovniPartner poslovniPartner) {

        return modelMapper.map(poslovniPartner, PoslovniPartnerDTO.class);
    }

    public List<PoslovniPartnerDTO> konvertujEntityToDto(List<PoslovniPartner> poslovniPartneri) {
        List<PoslovniPartnerDTO> listaPoslovnihPartneraDTO = new ArrayList<PoslovniPartnerDTO>();
        for (PoslovniPartner poslovniPartner : poslovniPartneri) {
            listaPoslovnihPartneraDTO.add(konvertujEntityToDto(poslovniPartner));
        }

        return listaPoslovnihPartneraDTO;
    }

}
