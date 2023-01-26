package com.ftn.poslovnainformatika.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Where(clause = "obrisano='false'")
@SQLDelete(sql="UPDATE roba SET obrisano=true Where id=?")
public class Roba {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "naziv_robe")
	private String nazivRobe;

	@Column(name = "jedinica_mere")
	private String jedinicaMere;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "grupa_robe_id")
	private GrupaRobe grupaRobe;

	@OneToMany(mappedBy = "roba", cascade = CascadeType.ALL)
	private Set<StavkeCenovnika> stavkeCenovnika = new HashSet<>();

	@OneToMany(mappedBy = "roba", cascade = CascadeType.ALL)
	private Set<StavkaFakture> stavkeFakture = new HashSet<>();

	private boolean obrisano;

}
