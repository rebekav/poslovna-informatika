package com.ftn.poslovnainformatika.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
public class Narudzbenica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long brojNarudzbenice;

	private Date datumNarudzbenice;

	private boolean tipNarudzbenice;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "poslovna_godina_id")
	private PoslovnaGodina poslovnaGodina;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "poslovni_partner_id")
	private PoslovniPartner poslovniPartner;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "preduzece_id")
	private Preduzece preduzece;

	@OneToMany(mappedBy = "narudzbenica", cascade = CascadeType.ALL)
	private Set<StavkaNarudzbenice> stavkeNarudzbenice = new HashSet<>();

	@OneToMany(mappedBy = "narudzbenica", cascade = CascadeType.ALL)
	private Set<Otpremnica> otpremnice = new HashSet<>();

	@OneToMany(mappedBy = "narudzbenica", cascade = CascadeType.ALL)
	private Set<Faktura> fakture = new HashSet<>();

	private boolean obrisano;

}
