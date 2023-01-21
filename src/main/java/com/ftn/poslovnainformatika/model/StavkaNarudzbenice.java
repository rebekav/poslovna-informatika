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
public class StavkaNarudzbenice {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "jedinica_mere")
	private String jedinicaMere;

	private int kolicina;

	private String opis;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "narudzbenica_id")
	private Narudzbenica narudzbenica;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roba_id")
	private Roba roba;

	private boolean obrisano;

}
