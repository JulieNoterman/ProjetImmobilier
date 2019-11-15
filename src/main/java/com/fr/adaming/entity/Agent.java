package com.fr.adaming.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
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
	
	@OneToMany(mappedBy = "agent")
	private List<Client> client;

	public Agent(String pwd, LocalDateTime dateRecrutement, List<Client> client) {
		super();
		this.pwd = pwd;
		this.dateRecrutement = dateRecrutement;
		this.client = client;
	}

	public Agent(String email, String fullname, Long telephone) {
		super(email, fullname, telephone);
		// TODO Auto-generated constructor stub
	}

	
	
}
