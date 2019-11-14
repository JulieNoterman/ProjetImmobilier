package com.fr.adaming.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter 
@Setter 
@NoArgsConstructor 
@ToString
public class Agent extends User {

	@Column(nullable = false)
	@Size(min = 8, max = 16, message = "Le nombre de caratère de votre mot de passe est incorrect")
	private String pwd;
	
	private LocalDateTime dateRecrutement;
}
