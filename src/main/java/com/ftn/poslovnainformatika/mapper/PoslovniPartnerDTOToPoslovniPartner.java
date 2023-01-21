package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.PoslovniPartnerDTO;
import com.ftn.poslovnainformatika.model.PoslovniPartner;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PoslovniPartnerDTOToPoslovniPartner {

    @Autowired
    private ModelMapper modelMapper;

    public PoslovniPartner konvertujDTOToEntity(PoslovniPartnerDTO poslovniPartnerDTO) {
        PoslovniPartner poslovniPartner = modelMapper.map(poslovniPartnerDTO, PoslovniPartner.class);

        return poslovniPartner;
    }

}
