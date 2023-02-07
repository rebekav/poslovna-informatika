package com.ftn.poslovnainformatika.service;

import com.ftn.poslovnainformatika.dto.OtpremnicaDTO;
import com.ftn.poslovnainformatika.model.Narudzbenica;
import com.ftn.poslovnainformatika.model.Otpremnica;

import java.util.List;

public interface IOtpremnicaService {

    List<Otpremnica> findAll();

    void save(Otpremnica otpremnica);

    void update(Otpremnica otpremnica);

    Otpremnica getOne(long idotpremnice);

    void kreirajOtpremnicuOdNaruzbenice(Narudzbenica narudzbenica);

    void kreirajFakturuOdOtpremnice(OtpremnicaDTO otpremnicaDTO, int poslednjaPoslovnjaGodina);

}