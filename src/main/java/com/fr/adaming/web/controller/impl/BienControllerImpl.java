package com.fr.adaming.web.controller.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.entity.Client;
import com.fr.adaming.service.IBienService;
import com.fr.adaming.web.controller.IBienController;


@RestController
@RequestMapping(path = "projetimmo/bien")
public class BienControllerImpl implements IBienController {

	@Autowired
	@Qualifier ("bienServiceImpl")
	private IBienService service;
	
	
	@GetMapping(path = "/get-all")
	public List<Bien> getAllBiens() {
		
		return service.findAll();
	}
	
	@GetMapping(path = "/get-nonvendu")
	public List<Bien> getBiensNonVendu() {
		
		return service.findNonVendu();
	}
	
	@GetMapping(path = "/get-id/{id}")
	public Optional<Bien> getOneById(@PathVariable(name = "id") Long id) {
		return service.findById(id);
	}
	
	@RequestMapping(path="/save", method = RequestMethod.POST)
	public String save(@RequestBody Bien bien) {
		if(service.save(bien) != null) {
			return "SUCESS";}
			else {return "FAIL";}
	}
	
	@PutMapping(path="/update")
	public String update(@RequestBody Bien bien) {
		if(service.update(bien) != null) {
			return "SUCESS";}
			else {return "FAIL";}
	}
	
	
	@RequestMapping(path = "/get-delete/{id}",method = RequestMethod.DELETE )
	public void deleteById(@RequestBody Bien bien) {
		 service.delete(bien);
	}

	
	
	
	
	
}
