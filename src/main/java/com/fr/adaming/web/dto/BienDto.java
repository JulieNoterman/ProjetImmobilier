package com.fr.adaming.web.dto;





import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
public class BienDto {

	
	private Long id;
	
	
	@NotNull
	private int prix;
	
	
	private boolean vendu;



	
	
	
}
