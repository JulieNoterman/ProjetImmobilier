package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @NoArgsConstructor @ToString
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	protected Long id;
	
	@Column(unique = true, nullable = false)
	protected String email;
	
	@Column(nullable = false)
	protected String fullname;
	

	protected Long telephone;


	public User(String email, String fullname, Long telephone) {
		super();
		this.email = email;
		this.fullname = fullname;
		this.telephone = telephone;
	}
	
	
}
