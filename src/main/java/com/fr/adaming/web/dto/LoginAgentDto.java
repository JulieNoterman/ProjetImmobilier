package com.fr.adaming.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class LoginAgentDto {

	@Email
	@NotNull
	@NotEmpty
	@NotBlank
	private String email;

	@NotNull
	@NotEmpty
	@NotBlank
	private String pwd;
}
