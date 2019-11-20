package com.fr.adaming.web.dto;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import com.fr.adaming.entity.Client;

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
	
	
	private Long id;
	
	@Digits(integer=10, fraction = 0)
	private Long telephone;
	
	private LocalDateTime dateRecrutement;
	
	private List<Client> client;
}
