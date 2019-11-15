package com.fr.adaming.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Bien;
import com.fr.adaming.repository.BienRepository;
import com.fr.adaming.service.*;

@Service
public class BienServiceImpl implements IBienService {

	
	@Autowired
	private BienRepository dao;

	/**
	 * Save a given bien in the database
	 * 
	 * @param bien - the given entity
	 * @return updated bien if the given entity is not already in the database
	 */
	@Override
	public Bien save(Bien bien) {
		if (dao.exists(Example.of(bien))) {
			return null;
		} else {
			return dao.save(bien);
		}
	}
	
	/**
	 * List
	 * 
	 * @return List<Bien>
	 */
	@Override
	public List<Bien> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	
	/**
	 * List
	 * 
	 * @return List<Bien> where vendu = false
	 */
	public List<Bien> findNonVendu() {
		// TODO Auto-generated method stub
		return dao.listNonVendu();
	}

	/**
	 * Save a given bien in the database
	 * 
	 * @param bien - the given entity
	 * @return updated bien if the given entity is not already in the database else return null
	 */
	@Override
	public Bien update(Bien bien) {
		if (findById(bien.getId()) != null) {
			dao.save(bien);
			return bien;
		} else {
			return null;
		}
	}

	/**
	 * Delete the given entity from the database
	 * 
	 * @param bien
	 */
	@Override
	public boolean delete(Bien bien) {
		// TODO Auto-generated method stub
		if (findById(bien.getId()) != null) {
			dao.delete(bien);
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Switch the state vente to true if the bien already exist in the database
	 * 
	 * @param societe - the given entity
	 * @return true if the bien is vendu else return false
	 */
	@Override
	public boolean vente(Bien bien) {
		// TODO Auto-generated method stub
		if (findById(bien.getId()) != null) {
			dao.vente(bien.getId());
			return true;
		} else {
			return false;
		}
	}
	
	/**
	 * Find a row in the database
	 * 
	 * @param id (Long)
	 * @return bien
	 */
	public Bien findById(Long id) {
		try {
			return dao.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	
	
	
}
