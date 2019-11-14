package com.fr.adaming.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.validation.constraints.Pattern;

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
	
<<<<<<< HEAD
	@Pattern(regexp = "^[0-9]{10}$")
=======
>>>>>>> 2ae824fa85512f35c781226a472488140db8b1b3
	protected Long telephone;
}
