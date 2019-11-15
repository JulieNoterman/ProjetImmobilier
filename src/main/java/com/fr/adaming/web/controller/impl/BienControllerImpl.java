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
	
	
	
	public List<Bien> getAllBiens() {
		
		return service.findAll();
	}
	
	
	public List<Bien> getBiensNonVendu() {
		
		return service.findNonVendu();
	}
	
	
	public Bien getOneById(@PathVariable(name = "id") Long id) {
		return service.findById(id);
	}
	
	
	public String save(@RequestBody BienDto bienDto) {
		if(service.save(BienDtoConverter.convertToDto(bienDto)) != null) {
			return "SUCESS";}
			else {return "FAIL";}
	}
	
	
	public String update(@RequestBody BienDto bienDto) {
		if(service.update(BienDtoConverter.convertToDto(bienDto)) != null) {
			return "SUCESS";}
			else {return "FAIL";}
	}
	
	
	
	public void deleteById(@RequestBody Bien bien) {
		 service.delete(bien);
	}


	public void venteById(@RequestBody Bien bien) {
		service.vente(bien);
		
	}

	
	
	
	
	
}
