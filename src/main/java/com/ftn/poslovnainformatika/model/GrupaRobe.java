package com.ftn.poslovnainformatika.model;

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
import org.hibernate.annotations.Where;

@Entity
@Where(clause = "obrisano='false'")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class GrupaRobe {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String naziv;

	@OneToMany(mappedBy = "grupaRobe", cascade = CascadeType.ALL)
	private Set<Roba> robe = new HashSet<>();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "preduzece_id")
	private Preduzece preduzece;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stopa_pdv_id")
	private StopaPdv stopaPdv;

	private boolean obrisano;

}
