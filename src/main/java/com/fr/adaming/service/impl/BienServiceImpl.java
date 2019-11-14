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

	@Override
	public Bien save(Bien bien) {
		if (dao.exists(Example.of(bien))) {
			return null;
		} else {
			return dao.save(bien);
		}
	}

	@Override
	public List<Bien> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}
	
	
	public List<Bien> findNonVendu() {
		// TODO Auto-generated method stub
		return dao.listNonVendu();
	}

	@Override
	public Bien update(Bien bien) {
		if (findById(bien.getId()) != null) {
			dao.save(bien);
			return bien;
		} else {
			return null;
		}
	}

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
	
	public Optional<Bien> findById(Long id) {
		try {
			return dao.findById(id);
		} catch (NoSuchElementException e) {
			return null;
		}
	}
	
	
	
	
}
