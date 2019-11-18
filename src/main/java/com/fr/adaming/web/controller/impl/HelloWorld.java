package com.fr.adaming.web.controller.impl;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.service.IClientService;
import com.fr.adaming.web.dto.ClientSaveDto;
import com.fr.adaming.web.dto.converter.ClientDtoConverter;

@RestController
@RequestMapping(path = "api/hello")
public class HelloWorld {
	

	@Autowired
	private IClientService service;
	
	@GetMapping
	public String SayHello() {
		return "Coucou";
	}
	
	@PostMapping
	public ClientSaveDto save(@Valid @RequestBody ClientSaveDto dto) {
		return ClientDtoConverter.convertToDto(service.save( ClientDtoConverter.convertToDto(dto)));   
		
	}
	
}
