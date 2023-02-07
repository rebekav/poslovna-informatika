package com.ftn.poslovnainformatika.model;

import java.util.*;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Narudzbenica {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private long brojNarudzbenice;

	private Date datumNarudzbenice;

	@Column(name = "tip_narudzbenice")
	private TipNarudzbenice tipNarudzbenice;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "poslovna_godina_id")
	private PoslovnaGodina poslovnaGodina;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "poslovni_partner_id")
	private PoslovniPartner poslovniPartner;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "preduzece_id")
	private Preduzece preduzece;

	@OneToMany(mappedBy = "narudzbenica", cascade = CascadeType.ALL)
	private Set<StavkaNarudzbenice> stavkeNarudzbenice = new HashSet<>();

	@OneToMany(mappedBy = "narudzbenica", cascade = CascadeType.ALL)
	private Set<Otpremnica> otpremnice = new HashSet<>();

	@OneToMany(mappedBy = "narudzbenica", cascade = CascadeType.MERGE)
	private Set<Faktura> fakture = new HashSet<>();

	private boolean obrisano;

	public enum TipNarudzbenice {
		ULAZNA(0), IZLAZNA(1);

		private int value;
		private static Map map = new HashMap();

		private TipNarudzbenice(int value) {
			this.value = value;
		}

		static {
			for (TipNarudzbenice tipNarudzbenice : TipNarudzbenice.values()) {
				map.put(tipNarudzbenice.value, tipNarudzbenice);
			}
		}

		public static TipNarudzbenice valueOf(int tip) {
			return (TipNarudzbenice) map.get(tip);
		}

		public int getValue() {
			return value;
		}
	}

}
