package com.ftn.poslovnainformatika.model;

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

import org.hibernate.annotations.SQLDelete;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Preduzece {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "naziv_preduzeca")
	private String nazivPreduzeca;

	@Column(name = "pib")
	private String PIB;

	@Column(name = "tekuci_racun")
	private String tekuciRacun;

	@Column(name = "email")
	private String emailAdresa;

	private String adresa;

	private String telefon;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mesto_id")
	private Mesto mesto;

	@OneToMany(mappedBy = "preduzece", cascade = CascadeType.ALL)
	private Set<PoslovniPartner> poslovniPartneri = new HashSet<>();

	@OneToMany(mappedBy = "preduzece", cascade = CascadeType.ALL)
	private Set<Narudzbenica> narudzbenice = new HashSet<>();

	@OneToMany(mappedBy = "preduzece", cascade = CascadeType.ALL)
	private Set<Otpremnica> otpremnice = new HashSet<>();

	@OneToMany(mappedBy = "preduzece", cascade = CascadeType.ALL)
	private Set<Faktura> fakture = new HashSet<>();

	@OneToMany(mappedBy = "preduzece", cascade = CascadeType.ALL)
	private Set<Cenovnik> cenovnici = new HashSet<>();

	@OneToMany(mappedBy = "preduzece", cascade = CascadeType.ALL)
	private Set<GrupaRobe> grupeRobe = new HashSet<>();

}
