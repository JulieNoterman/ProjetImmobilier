package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity @Getter @Setter @NoArgsConstructor @ToString
public class User {

	@Id
	@GeneratedValue
	private Long id;
	
	@Column(unique = true, nullable = false)
	private String email;
}
