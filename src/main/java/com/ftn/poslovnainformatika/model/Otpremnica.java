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
public class Otpremnica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long brojOtpremnice;

	private Date datumOtpremnice;

	private boolean tipOtpremnice;

	private double racunOtpremnice;

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

	@OneToMany(mappedBy = "otpremnica", cascade = CascadeType.ALL)
	private Set<StavkaOtpremnice> stavkeOtpremnice = new HashSet<>();

	@OneToMany(mappedBy = "otpremnica", cascade = CascadeType.MERGE)
	private Set<Faktura> fakture = new HashSet<>();

	private boolean obrisano;

}
