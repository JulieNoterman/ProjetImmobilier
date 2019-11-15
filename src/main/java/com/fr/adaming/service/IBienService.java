package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Bien;




public interface IBienService {

	public Bien save(Bien bien);
	public List<Bien> findAll();
	public Bien update(Bien bien);
	public boolean delete(Bien bien);
	public boolean vente(Bien bien);
	public Bien findById(Long id);
	public List<Bien> findNonVendu();
	
	
}
