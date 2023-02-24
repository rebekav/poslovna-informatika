package com.ftn.poslovnainformatika.service.impl;

import com.ftn.poslovnainformatika.dto.NarudzbenicaDTO;
import com.ftn.poslovnainformatika.mapper.NarudzbenicaDTOToNarudzbenica;
import com.ftn.poslovnainformatika.model.*;
import com.ftn.poslovnainformatika.repository.NarudzbenicaRepository;
import com.ftn.poslovnainformatika.service.ICenovnikService;
import com.ftn.poslovnainformatika.service.IFakturaService;
import com.ftn.poslovnainformatika.service.INarudzbenicaService;
import com.ftn.poslovnainformatika.service.IStavkeFakture;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.*;
import java.util.stream.Collectors;

@Service
public class NarudzbenicaService implements INarudzbenicaService {

    @Autowired
    ICenovnikService cenovnikService;

    @Autowired
    IFakturaService fakturaService;

    @Autowired
    IStavkeFakture stavkeFakture;

    @Autowired
    NarudzbenicaDTOToNarudzbenica narudzbenicaDTOToNarudzbenica;

    @Autowired
    NarudzbenicaRepository narudzbenicaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Narudzbenica> findAll() {
        return narudzbenicaRepository.findAllNarudzbenice();
    }

    @Override
    @Transactional(readOnly = true)
    public Narudzbenica getOne(long id) {
        return narudzbenicaRepository.getOne(id);
    }

    @Override
    public void save(Narudzbenica narudzbenica) {
        narudzbenicaRepository.save(narudzbenica);


    }

    @Override
    @Transactional
    public void kreirajFakturuOdNarudzbenice(NarudzbenicaDTO narudzbenicaDTO, int poslednjaPoslovnjaGodina) {
        Narudzbenica narudzbenica = narudzbenicaDTOToNarudzbenica.konvertujDtoToEntity(narudzbenicaDTO);
        narudzbenica.setObrisano(true);
        save(narudzbenica);



        Faktura faktura = new Faktura();
        faktura.setBrojFakture(poslednjaPoslovnjaGodina + 1);
        faktura.setDatumFakture(new Date());
        faktura.setDatumValute(new Date());
        faktura.setPlaceno(true);
        if(narudzbenica.getPoslovniPartner().getTipPoslovnogPartnera() == PoslovniPartner.TipPoslovnogPartnera.KUPAC) {
            faktura.setVrstaFakture(false);
        }
        else {
            faktura.setVrstaFakture(true);
        }
        faktura.setPreduzece(narudzbenica.getPreduzece());
        faktura.setPoslovnaGodina(narudzbenica.getPoslovnaGodina());
        faktura.setPoslovniPartner(narudzbenica.getPoslovniPartner());
        faktura.setNarudzbenica(narudzbenica);


        Set<StavkaNarudzbenice> stavkeNarudzbenice = narudzbenica.getStavkeNarudzbenice();


        List<Cenovnik> cenovnici = new ArrayList<Cenovnik>();

        List<Cenovnik>cenovniciList = cenovnikService.findAll().stream()
                .filter(cen -> cen.getPoslovniPartner().getId() == narudzbenica.getPoslovniPartner().getId())
                .collect(Collectors.toList());

        for (Cenovnik cenovnik : cenovniciList) {
            cenovnici.add(cenovnik);
        }


        List<StavkeCenovnika> stavkeCenovnika = new ArrayList<StavkeCenovnika>();

        for (Cenovnik c : cenovnici) {
            for(StavkeCenovnika s : c.getCene()) {
                stavkeCenovnika.add(s);
            }
        }


        Set<StavkaFakture> nadjeneStavke = new HashSet<>();

        for (StavkaNarudzbenice sn : stavkeNarudzbenice) {

            for(StavkeCenovnika sc : stavkeCenovnika) {

                if(sc.getRoba().getId() == sn.getRoba().getId()) {


                    Set<StopaPdv> stopePDva = sn.getRoba().getGrupaRobe().getPdv().getStopePdv();
                    StopaPdv stopaPdv = stopePDva.stream().filter(sp -> !sp.isObrisano()).findFirst().get();

                    StavkaFakture novaStavkaFakture = new StavkaFakture();
                    novaStavkaFakture.setIznosPDV(sc.getCena() * sn.getKolicina() * (stopaPdv.getProcenat() / 100));
                    novaStavkaFakture.setUkupanIznos(sc.getCena() * sn.getKolicina() * (1+(stopaPdv.getProcenat() / 100)));
                    novaStavkaFakture.setCena(sc.getCena());
                    novaStavkaFakture.setKolicina(sn.getKolicina());
                    novaStavkaFakture.setOsnovicaPDV(sc.getCena() * sn.getKolicina());
                    novaStavkaFakture.setObrisano(false);
                    novaStavkaFakture.setRabat(0);
                    novaStavkaFakture.setRoba(sn.getRoba());
                    novaStavkaFakture.setProcenatPDV(stopaPdv.getProcenat());
                    novaStavkaFakture.setFaktura(faktura);
                    nadjeneStavke.add(novaStavkaFakture);
                }
            }
        }

        faktura.setStavkeFakture(nadjeneStavke);
        fakturaService.update(faktura);

    }

}
