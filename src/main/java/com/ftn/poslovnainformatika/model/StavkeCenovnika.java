package com.ftn.poslovnainformatika.model;

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
public class StavkeCenovnika {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private float cena;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "cjenovnik_id")
	private Cenovnik cenovnik;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "roba_id")
	private Roba roba;

	private boolean obrisano;

}
