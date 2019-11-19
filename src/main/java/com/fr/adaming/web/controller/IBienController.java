package com.fr.adaming.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.web.dto.BienDto;

@RequestMapping(path = "api/projetimmo/bien")
public interface IBienController {
	
	@GetMapping(path = "/get-all")
	public List<BienDto> getAllBiens();
	
	@GetMapping(path = "/get-nonvendu")
	public List<BienDto> getBiensNonVendu();
	
	@GetMapping(path = "/get-id/{id}")
	public BienDto getOneById(@PathVariable(name = "id") Long id);
	
	@RequestMapping(path="/save", method = RequestMethod.POST)
	public BienDto save(@RequestBody @Valid BienDto bienDto);
	
	@PutMapping(path="/update")
	public BienDto update(@RequestBody @Valid BienDto bienDto);
	
	@RequestMapping(path = "/get-delete/{id}",method = RequestMethod.DELETE )
	public void delete(@RequestBody Bien bien);
	
	@RequestMapping(path = "/vente/{id}",method = RequestMethod.PUT )
	public void venteById(@RequestBody Bien bien);
}
