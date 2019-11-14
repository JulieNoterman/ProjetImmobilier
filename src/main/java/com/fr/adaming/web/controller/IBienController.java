package com.fr.adaming.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import com.fr.adaming.entity.Bien;

public interface IBienController {

	public List<Bien> getAllBiens();
	public List<Bien> getBiensNonVendu();
	public Optional<Bien> getOneById(Long id);
	public String save(Bien bien);
	public String update(Bien bien);
	public void deleteById(Bien bien);
}
