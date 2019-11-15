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
import com.fr.adaming.web.dto.BienDto;

public interface IBienController {
	
	@GetMapping(path = "/get-all")
	public List<Bien> getAllBiens();
	
	@GetMapping(path = "/get-nonvendu")
	public List<Bien> getBiensNonVendu();
	
	@GetMapping(path = "/get-id/{id}")
	public Bien getOneById(Long id);
	
	@RequestMapping(path="/save", method = RequestMethod.POST)
	public String save(BienDto bienDto);
	
	@PutMapping(path="/update")
	public String update(BienDto bienDto);
	
	@RequestMapping(path = "/get-delete/{id}",method = RequestMethod.DELETE )
	public void deleteById(Bien bien);
	
	@RequestMapping(path = "/vente/{id}",method = RequestMethod.PUT )
	public void venteById(Bien bien);
}
