package com.fr.adaming.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;

import com.fr.adaming.web.dto.BienDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @NoArgsConstructor @ToString
public class Bien {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false )
	@Min(value = 1, message = "The value must be positive")
	private int prix;
	
	@Column(nullable = false)
	private boolean vendu;
	
	public Bien(Long id, int prix, boolean vendu) {
		super();
		this.id = id;
		this.prix = prix;
		this.vendu = vendu;
	}
	
	public Bien(int prix, boolean vendu) {
		super();
		this.prix = prix;
		this.vendu = vendu;
	}
	
	@ManyToOne (cascade = CascadeType.ALL )
	@JoinColumn(name="id_client")
	private Client client;
	
	
	public Bien(BienDto dto) {
		super();		
		this.prix = dto.getPrix();
		this.vendu = dto.isVendu();
	}
	
	
	
	
}
