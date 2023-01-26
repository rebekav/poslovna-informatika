package com.ftn.poslovnainformatika.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

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

	@OneToMany(
			fetch = FetchType.LAZY,
			cascade = CascadeType.ALL,
			mappedBy = "grupaRobe"
	)
	private Set<Roba> robe = new HashSet<>();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "preduzece_id")
	private Preduzece preduzece;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "stopa_pdv_id")
	private StopaPdv stopaPdv;

	private boolean obrisano;

}
