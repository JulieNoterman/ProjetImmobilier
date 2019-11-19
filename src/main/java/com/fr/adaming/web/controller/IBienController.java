package com.fr.adaming.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.web.dto.BienDto;

public interface IBienController {
	
	@GetMapping(path = "/get-all")
	public List<BienDto> getAllBiens();
	
	@GetMapping(path = "/get-nonvendu")
	public List<BienDto> getBiensNonVendu();
	
	@GetMapping(path = "/get-id/{id}")
	public BienDto getOneById(Long id);
	
	@RequestMapping(path="/save", method = RequestMethod.POST)
	public BienDto save(BienDto bienDto);
	
	@PutMapping(path="/update")
	public BienDto update(BienDto bienDto);
	
	@RequestMapping(path = "/get-delete/{id}",method = RequestMethod.DELETE )
	public void deleteById(Bien bien);
	
	@RequestMapping(path = "/vente/{id}",method = RequestMethod.PUT )
	public void venteById(Bien bien);
}
