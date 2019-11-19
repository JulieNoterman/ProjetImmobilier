package com.fr.adaming.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.service.IBienService;
import com.fr.adaming.web.controller.IBienController;
import com.fr.adaming.web.dto.BienDto;
import com.fr.adaming.web.dto.converter.BienDtoConverter;


@RestController
public class BienControllerImpl implements IBienController {

	@Autowired
	@Qualifier ("bienServiceImpl")
	private IBienService service;
	
	
	
	public List<BienDto> getAllBiens() {
		
		return BienDtoConverter.convertBien(service.findAll());
	}
	
	
	public List<BienDto> getBiensNonVendu() {
		
		return BienDtoConverter.convertBien(service.findNonVendu());
	}
	
	
	public BienDto getOneById(Long id) {
		return BienDtoConverter.convertToBien(service.findById(id));
	}
	
	
	public BienDto save(BienDto bienDto) {
		Bien b = service.save(BienDtoConverter.convertToDto(bienDto));
		
		if (b != null) {
			return BienDtoConverter.convertToBien(b); 
		}
		return null;
		
	}
	
	
	public BienDto update(BienDto bienDto) {
		Bien b = service.update(BienDtoConverter.convertToDto(bienDto));
		
		if (b != null) {
			return BienDtoConverter.convertToBien(b); 
		}
		return null;
		
	}
	
	
	
	public void delete(Bien bien) {
		 service.delete(bien);
	}


	public void venteById(Bien bien) {
		service.vente(bien);
		
	}

	
	
	
	
	
}
