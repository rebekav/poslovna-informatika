package com.ftn.poslovnainformatika.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.LAZY;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Pdv {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String vrstaPdv;

	@OneToMany(mappedBy = "pdv", cascade = ALL)
	private Set<StopaPdv> stopePdv = new HashSet<>();

	@OneToMany(cascade = { ALL }, fetch = LAZY, mappedBy = "pdv")
	private Set<GrupaRobe> grupeRobe = new HashSet<>();

	private boolean obrisano;

}
