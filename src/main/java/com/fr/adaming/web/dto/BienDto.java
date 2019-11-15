package com.fr.adaming.web.dto;




import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString @NoArgsConstructor
public class BienDto {

	
	private Long id;
	
	@NotBlank
	@NotEmpty
	@NotNull
	@Min(value = 0, message = "The value must be positive")
	private int prix;
	
	
	private boolean vendu;



	
	
	
}
