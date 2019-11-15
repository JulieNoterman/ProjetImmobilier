package com.fr.adaming.web.dto.converter;

import java.util.ArrayList;
import java.util.List;


import com.fr.adaming.entity.Bien;

import com.fr.adaming.web.dto.BienDto;



public class BienDtoConverter {

	
	public static Bien convertToDto(BienDto bienDto) {
		Bien bien = new Bien();
		bien.setId(bienDto.getId());
		bien.setPrix(bienDto.getPrix());
		bien.setVendu(bienDto.isVendu());
		
		return bien;
		
		
	}
	
	public static BienDto convertToBien (Bien bien) {
		BienDto bienDto = new BienDto();
		bienDto.setId(bien.getId());
		bienDto.setPrix(bien.getPrix());
		bienDto.setVendu(bien.isVendu());
		return bienDto;
	}
	
	public static List<Bien> convertDto(List<BienDto> bienDto) {
		List<Bien> list = new ArrayList<>();
		for(BienDto dto : bienDto) {
			list.add(BienDtoConverter.convertToDto(dto));
		}
		return list;
	}
	
	public static List<BienDto> convertAgent(List<Bien> bien) {
		List<BienDto> list = new ArrayList<>();
		for(Bien dto : bien) {
			list.add(BienDtoConverter.convertToBien(dto));
		}
		return list;
	}
	
	
}
