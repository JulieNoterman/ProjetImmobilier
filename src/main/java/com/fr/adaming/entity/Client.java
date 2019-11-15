package com.fr.adaming.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fr.adaming.enumeration.TypeClient;
import com.fr.adaming.web.dto.ClientSaveDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @NoArgsConstructor @ToString
public class Client extends User{
	
	@Column(nullable = false)
	private TypeClient type;
	
	
	@OneToMany(mappedBy = "client")
	private List<Bien> bien;
	
	@ManyToOne(cascade = CascadeType.ALL )
	@JoinColumn(name = "id_agent")
	private Agent agent;


	public Client(ClientSaveDto dto) {
		this.fullname = dto.getFullname();
		this.email = dto.getEmail();
		this.type = dto.getType();
	}
	
	
	
	
	

}
