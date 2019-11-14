package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import com.fr.adaming.enumeration.TypeClient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class Client extends User{
	
	@Column(nullable = false)
	private TypeClient type;
	
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_client")
	private List<Bien> bien;

}
