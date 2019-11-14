package com.fr.adaming.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
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
	@NotEmpty
	@NotBlank
	private TypeClient type;

}
