package com.fr.adaming.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fr.adaming.repository.BienRepository;
import com.fr.adaming.service.*;

@Service
public class BienServiceImpl implements IBienService {

	
	@Autowired
	private BienRepository dao;
}
