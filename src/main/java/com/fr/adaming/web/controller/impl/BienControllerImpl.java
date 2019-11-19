package com.fr.adaming.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.service.IBienService;
import com.fr.adaming.web.controller.IBienController;
import com.fr.adaming.web.dto.BienDto;
import com.fr.adaming.web.dto.converter.BienDtoConverter;


@RestController
@RequestMapping(path = "api/projetimmo/bien")
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
	
	
	public BienDto getOneById(@PathVariable(name = "id") Long id) {
		return BienDtoConverter.convertToBien(service.findById(id));
	}
	
	
	public BienDto save(@RequestBody BienDto bienDto) {
		return BienDtoConverter.convertToBien(service.save(BienDtoConverter.convertToDto(bienDto))); 
		
	}
	
	
	public BienDto update(@RequestBody BienDto bienDto) {
		return BienDtoConverter.convertToBien((service.update(BienDtoConverter.convertToDto(bienDto))));
	}
	
	
	
	public void deleteById(@RequestBody Bien bien) {
		 service.delete(bien);
	}


	public void venteById(@RequestBody Bien bien) {
		service.vente(bien);
		
	}

	
	
	
	
	
}
