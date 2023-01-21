package com.ftn.poslovnainformatika.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class StavkaOtpremnice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "jedinica_mere")
	private String jedinicaMere;

	private int kolicina;

	private String opis;

	private float cena;

	@Column(name = "ukupan_iznos")
	private float ukupanIznos;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "otpremnica_id")
	private Otpremnica otpremnica;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roba_usluga_id")
	private Roba roba;

	private boolean obrisano;

}
