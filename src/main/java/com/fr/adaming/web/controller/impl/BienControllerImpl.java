package com.fr.adaming.web.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.service.IBienService;
import com.fr.adaming.web.controller.IBienController;

@RestController
public class BienControllerImpl implements IBienController {

	@Autowired
	@Qualifier ("BienServiceImpl")
	private IBienService service;
}
