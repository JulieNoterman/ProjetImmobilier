package com.fr.adaming.web.dto.converter;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.entity.Client;
import com.fr.adaming.web.dto.BienDto;

public class BienDtoConverter {

	
	public Bien BienToBienDto(BienDto bienDto) {
		Bien bien = new Bien();
		bien.setId(bienDto.getId());
		bien.setPrix(bienDto.getPrix());
		bien.setVendu(bienDto.isVendu());
		
		return bien;
		
		
	}
	
	public BienDto BienDtoToBien (Bien bien) {
		BienDto bienDto = new BienDto();
		bienDto.setId(bien.getId());
		bienDto.setPrix(bien.getPrix());
		bienDto.setVendu(bien.isVendu());
		return bienDto;
	}
	
	
}
