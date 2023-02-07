package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.dto.OtpremnicaDTO;
import com.ftn.poslovnainformatika.mapper.OtpremnicaDTOToOtpremnica;
import com.ftn.poslovnainformatika.model.*;
import com.ftn.poslovnainformatika.repository.OtpremnicaRepository;
import com.ftn.poslovnainformatika.service.ICenovnikService;
import com.ftn.poslovnainformatika.service.IFakturaService;
import com.ftn.poslovnainformatika.service.INarudzbenicaService;
import com.ftn.poslovnainformatika.service.IOtpremnicaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OtpremnicaService implements IOtpremnicaService {

    @Autowired
    IFakturaService fakturaService;

    @Autowired
    OtpremnicaDTOToOtpremnica otpremnicaDTOToOtpremnica;

    @Autowired
    OtpremnicaRepository otpremnicaRepository;

    @Autowired
    INarudzbenicaService narudzbenicaService;

    @Autowired
    ICenovnikService cenovnikService;

    @Override
    public List<Otpremnica> findAll() {
        return otpremnicaRepository.findAllOtpremnice();
    }

    @Override
    public void save(Otpremnica otpremnica) {
        otpremnicaRepository.save(otpremnica);

    }

    @Override
    public Otpremnica getOne(long idotpremnice) {
        return otpremnicaRepository.getOne(idotpremnice);
    }

    @Override
    public void kreirajOtpremnicuOdNaruzbenice(Narudzbenica narudzbenica) {
        List<Otpremnica> listaOtpremnica = otpremnicaRepository.findAll();


        narudzbenica.setObrisano(true);
        narudzbenicaService.save(narudzbenica);

        Otpremnica otpremnica = new Otpremnica();
        otpremnica.setBrojOtpremnice(listaOtpremnica.size() + 1);
        otpremnica.setDatumOtpremnice(new Date());
        otpremnica.setPreduzece(narudzbenica.getPreduzece());
        otpremnica.setPoslovnaGodina(narudzbenica.getPoslovnaGodina());
        otpremnica.setPoslovniPartner(narudzbenica.getPoslovniPartner());
        otpremnica.setTipOtpremnice(false);
        otpremnica.setObrisano(false);
        otpremnica.setNarudzbenica(narudzbenica);


        List<Cenovnik> cenovnik = cenovnikService.findAll().stream()
                .filter(c -> c.getPreduzece().getId() == 1)
                .collect(Collectors.toList());


        Set<StavkaNarudzbenice> stavkeNarudzbenice = narudzbenica.getStavkeNarudzbenice();
        Set<StavkaOtpremnice> stavkeOtpremnice = new HashSet<>();
        List<StavkeCenovnika> stavkeCenovnika = new ArrayList<StavkeCenovnika>();

        for (Cenovnik c : cenovnik) {
            for(StavkeCenovnika s : c.getCene()) {
                stavkeCenovnika.add(s);
            }
        }




        for (StavkaNarudzbenice stavkaNarudzbenice : stavkeNarudzbenice) {


            for(StavkeCenovnika sc : stavkeCenovnika) {

                if(sc.getRoba().getId() == stavkaNarudzbenice.getRoba().getId()) {

                    StavkaOtpremnice stavkaOtpremnice = new StavkaOtpremnice();
                    stavkaOtpremnice.setJedinicaMere(stavkaNarudzbenice.getJedinicaMere());
                    stavkaOtpremnice.setKolicina(stavkaNarudzbenice.getKolicina());
                    stavkaOtpremnice.setRoba(stavkaNarudzbenice.getRoba());
                    stavkaOtpremnice.setCena(sc.getCena());
                    stavkaOtpremnice.setUkupanIznos(stavkaNarudzbenice.getKolicina() * sc.getCena());
                    stavkaOtpremnice.setOtpremnica(otpremnica);
                    stavkaOtpremnice.setObrisano(false);
                    stavkaOtpremnice.setOpis(stavkaNarudzbenice.getOpis());

                    stavkeOtpremnice.add(stavkaOtpremnice);
                }
            }
        }

        otpremnica.setStavkeOtpremnice(stavkeOtpremnice);
        update(otpremnica);

    }

    @Override
    public void update(Otpremnica otpremnica) {
        double ukupanIznos = 0;

        for(StavkaOtpremnice so : otpremnica.getStavkeOtpremnice()) {
            if(!so.isObrisano()) {
                ukupanIznos += so.getUkupanIznos();
            }
        }
        otpremnica.setRacunOtpremnice(ukupanIznos);
        save(otpremnica);

    }

    @Override
    public void kreirajFakturuOdOtpremnice(OtpremnicaDTO otpremnicaDTO, int brojFakture) {
        Otpremnica otpremnica = otpremnicaDTOToOtpremnica.konvertujDtoToEntity(otpremnicaDTO);
        otpremnica.setObrisano(true);
        save(otpremnica);


        Faktura faktura = new Faktura();
        faktura.setBrojFakture(brojFakture + 1);
        faktura.setDatumFakture(new Date());
        faktura.setDatumValute(new Date());
        faktura.setPlaceno(true);
        faktura.setVrstaFakture(false);
        faktura.setPreduzece(otpremnica.getPreduzece());
        faktura.setPoslovnaGodina(otpremnica.getPoslovnaGodina());
        faktura.setPoslovniPartner(otpremnica.getPoslovniPartner());
        faktura.setOtpremnica(otpremnica);

        // naci id robe usluga iz narudzbenice
        Set<StavkaOtpremnice> stavkeOtpremnice = otpremnica.getStavkeOtpremnice();

        Set<StavkaFakture> stavke = new HashSet<>();

        for(StavkaOtpremnice so : stavkeOtpremnice) {
            StavkaFakture stavkaFakture = new StavkaFakture();
            stavkaFakture.setIznosPDV(so.getUkupanIznos()* (so.getRoba().getGrupaRobe().getStopaPdv().getProcenat() / 100));
            stavkaFakture.setProcenatPDV(so.getRoba().getGrupaRobe().getStopaPdv().getProcenat());
            stavkaFakture.setCena(so.getCena());
            stavkaFakture.setKolicina(so.getKolicina());
            stavkaFakture.setOsnovicaPDV(so.getUkupanIznos());
            stavkaFakture.setRabat(0);
            stavkaFakture.setUkupanIznos(so.getUkupanIznos() * (1+(so.getRoba()).getGrupaRobe().getStopaPdv().getProcenat() / 100));
            stavkaFakture.setObrisano(false);
            stavkaFakture.setRoba(so.getRoba());

            stavkaFakture.setFaktura(faktura);

            stavke.add(stavkaFakture);
        }

        faktura.setStavkeFakture(stavke);
        fakturaService.update(faktura);

    }


}
