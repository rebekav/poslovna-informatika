package com.ftn.poslovnainformatika.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Where;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@Where(clause = "obrisano='false'")
public class PoslovniPartner {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "naziv_poslovnog_partnera")
	private String nazivPoslovnogPartnera;

	@Column(name = "tip_poslovnog_partnera")
	private TipPoslovnogPartnera tipPoslovnogPartnera; 

	private String PIB;

	private String adresa;

	@Column(name = "tekuci_racun")
	private String tekuciRacun;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "preduzece_id")
	private Preduzece preduzece;

	@OneToMany(mappedBy = "poslovniPartner", cascade = CascadeType.ALL)
	private Set<Narudzbenica> narudzbenice = new HashSet<>();

	@OneToMany(mappedBy = "poslovniPartner", cascade = CascadeType.ALL)
	private Set<Otpremnica> otpremnice = new HashSet<>();

	@OneToMany(mappedBy = "poslovniPartner", cascade = CascadeType.ALL)
	private Set<Faktura> fakture = new HashSet<>();

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "mesto_id")
	private Mesto mesto;

	@OneToMany(mappedBy = "poslovniPartner", cascade = CascadeType.ALL)
	private Set<Cenovnik> cenovnici = new HashSet<>();

	private boolean obrisano;
	
	public enum TipPoslovnogPartnera {
		KUPAC(0), DOBAVLJAC(1);

		private int value;
		private static Map map = new HashMap<>();

		private TipPoslovnogPartnera(int value) {
			this.value = value;
		}

		static {
			for (TipPoslovnogPartnera tipPoslovnogPartnera : TipPoslovnogPartnera.values()) {
				map.put(tipPoslovnogPartnera.value, tipPoslovnogPartnera);
			}
		}

		public static TipPoslovnogPartnera valueOf(int tip) {
			return (TipPoslovnogPartnera) map.get(tip);
		}

		public int getValue() {
			return value;
		}
	}

}
