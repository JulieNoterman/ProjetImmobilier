package com.fr.adaming.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fr.adaming.enumeration.TypeClient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ClientSaveDto {
	
	@Email
	@NotNull
	@NotEmpty
	@NotBlank
	private String email;
	
	@NotNull
	@NotEmpty
	@NotBlank
	private String fullname;
	
	@NotNull
	private TypeClient type;
	

//	@Pattern(regexp = "[0-9]{10}")
	@Digits(integer=10, fraction = 0)
	private Long telephone;

}
