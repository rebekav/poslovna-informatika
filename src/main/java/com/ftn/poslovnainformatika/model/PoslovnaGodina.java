package com.ftn.poslovnainformatika.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class PoslovnaGodina {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private int godinaPoslovanja;

	private boolean zakljucenaGodina;

	@OneToMany(mappedBy = "poslovnaGodina", cascade = CascadeType.ALL)
	private Set<Narudzbenica> narudzbenice = new HashSet<>();

	@OneToMany(mappedBy = "poslovnaGodina", cascade = CascadeType.ALL)
	private Set<Otpremnica> otpremnice = new HashSet<>();

	@OneToMany(mappedBy = "poslovnaGodina", cascade = CascadeType.ALL)
	private Set<Faktura> fakture = new HashSet<>();

	private boolean obrisano;

}
