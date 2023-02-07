package com.ftn.poslovnainformatika.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
public class StavkaFakture {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private double cena;

	private long kolicina;

	@Column(name = "osnovica_pdv")
	private double osnovicaPDV;

	@Column(name = "procenat_pdv")
	private double procenatPDV;

	@Column(name = "iznos_pdv")
	private double iznosPDV;

	private double rabat;

	@Column(name = "ukupan_iznos")
	private double ukupanIznos;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "roba_id")
	private Roba roba;

	@ManyToOne(cascade = CascadeType.MERGE)
	@JoinColumn(name = "faktura_id")
	private Faktura faktura;

	private boolean obrisano;

}
