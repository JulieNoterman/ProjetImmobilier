package com.fr.adaming.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class AgentSaveDto {

	@NotNull
	@Email
	private String email;
	
	@NotNull
	private String fullname;
	
	@NotNull
	private String pwd;
}
