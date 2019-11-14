package com.fr.adaming.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fr.adaming.entity.Bien;

public interface IBienController {
	
	@GetMapping(path = "/get-all")
	public List<Bien> getAllBiens();
	
	@GetMapping(path = "/get-nonvendu")
	public List<Bien> getBiensNonVendu();
	
	@GetMapping(path = "/get-id/{id}")
	public Optional<Bien> getOneById(Long id);
	
	@RequestMapping(path="/save", method = RequestMethod.POST)
	public String save(Bien bien);
	
	@PutMapping(path="/update")
	public String update(Bien bien);
	
	@RequestMapping(path = "/get-delete/{id}",method = RequestMethod.DELETE )
	public void deleteById(Bien bien);
	
	@RequestMapping(path = "/vente/{id}",method = RequestMethod.PUT )
	public void venteById(Bien bien);
}
