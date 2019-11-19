package com.fr.adaming.web.controller.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Client;
import com.fr.adaming.service.IClientService;
import com.fr.adaming.web.controller.IClientController;
import com.fr.adaming.web.dto.ClientSaveDto;
import com.fr.adaming.web.dto.converter.ClientDtoConverter;


@RestController
@RequestMapping(path = "api/projetimmo/client")
public class ClientControllerImpl implements IClientController{
	
	@Autowired
	private IClientService service;


	@Override
	public ClientSaveDto save(@Valid @RequestBody ClientSaveDto dto) {
		if(service.save( ClientDtoConverter.convertToDto(dto)) != null) {
			return dto;
		}else {
			return null;
		}
	}

	@Override
	public List<ClientSaveDto> findAll() {
		return ClientDtoConverter.convert(service.findAll());
	}

	@Override
	public ClientSaveDto update(@RequestBody ClientSaveDto dto) {
		return ClientDtoConverter.convertToDto(service.update(ClientDtoConverter.convertToDto(dto)));
	}

	@Override
	public ClientSaveDto delete(@RequestBody ClientSaveDto dto) {
		if(service.delete( ClientDtoConverter.convertToDto(dto)) == true) {
			return dto;
		}else {
			return null;
		}
				
	}

}
