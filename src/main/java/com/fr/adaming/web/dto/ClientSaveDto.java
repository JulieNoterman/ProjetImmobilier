package com.fr.adaming.web.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import java.util.List;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.entity.Bien;
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
	
	@NotNull
	private Long id;
	
	@Email
	@NotNull
	private String email;
	
	@NotNull
	@NotBlank
	private String fullname;
	
	@NotNull
	private TypeClient type;
	

//	@Pattern(regexp = "[0-9]{10}")
	@Digits(integer=10, fraction = 0)
	private Long telephone;
	
	private Agent agent;
	
	private List<Bien> bien;

}
