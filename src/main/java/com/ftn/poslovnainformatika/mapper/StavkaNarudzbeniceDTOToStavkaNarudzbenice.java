package com.ftn.poslovnainformatika.mapper;

import com.ftn.poslovnainformatika.dto.StavkaNarudzbeniceDTO;
import com.ftn.poslovnainformatika.model.StavkaNarudzbenice;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

 @Component
 public class StavkaNarudzbeniceDTOToStavkaNarudzbenice {

        @Autowired
        private ModelMapper modelMapper;

        public StavkaNarudzbenice konvertujDtoToEntity(StavkaNarudzbeniceDTO stavkaNarudzbeniceDTO) {

            StavkaNarudzbenice stavkaNarudzbenice = modelMapper.map(stavkaNarudzbeniceDTO, StavkaNarudzbenice.class);
            return stavkaNarudzbenice;
        }
    }

