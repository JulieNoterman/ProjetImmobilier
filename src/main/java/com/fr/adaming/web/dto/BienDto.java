package com.fr.adaming.web.dto;





import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.fr.adaming.entity.Client;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
public class BienDto {

	
	private Long id;
	
	
	
	@Min(value = 1, message = "The value must be positive")
	private int prix;
	
	
	private boolean vendu;
	
	private Client client;



	
	
	
}
