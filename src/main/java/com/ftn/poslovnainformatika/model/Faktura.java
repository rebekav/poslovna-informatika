package com.ftn.poslovnainformatika.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Faktura {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "broj_fakture")
	private long brojFakture;

	@Column(name = "datum_fakture")
	private Date datumFakture;

	@Column(name = "datum_valute")
	private Date datumValute;

	private double osnovica;

	@Column(name = "ukupan_pdv")
	private double ukupanPdv;

	private double rabat;

	@Column(name = "iznos_bez_rabata")
	private double iznosBezRabata;

	@Column(name = "iznos_za_placanje")
	private double iznosZaPlacanje;

	private boolean placeno;

	@Column(name = "vrsta_fakture")
	private boolean vrstaFakture;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "poslovna_godina_id")
	private PoslovnaGodina poslovnaGodina;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "poslovni_partner_id")
	private PoslovniPartner poslovniPartner;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "preduzece_id")
	private Preduzece preduzece;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "narudzbenica_id")
	private Narudzbenica narudzbenica;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "otpremnica_id")
	private Otpremnica otpremnica;

	@OneToMany(mappedBy = "faktura", cascade = CascadeType.ALL)
	private Set<StavkaFakture> stavkeFakture = new HashSet<>();

}
