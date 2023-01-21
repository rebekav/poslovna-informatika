package com.ftn.poslovnainformatika.model;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
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
public class Mesto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String grad;

	@Column(name = "postanski_broj")
	private Long postanskiBroj;

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "mesto")
	private Set<PoslovniPartner> poslovniPartneri = new HashSet<>();

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "mesto")
	private Set<Preduzece> preduzeca = new HashSet<>();

}
