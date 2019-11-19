package com.fr.adaming.web.controller.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Client;
import com.fr.adaming.service.IClientService;
import com.fr.adaming.web.controller.IClientController;
import com.fr.adaming.web.dto.ClientSaveDto;
import com.fr.adaming.web.dto.converter.ClientDtoConverter;


@RestController
public class ClientControllerImpl implements IClientController{
	
	@Autowired
	private IClientService service;


	@Override
	public ClientSaveDto save(@Valid @RequestBody ClientSaveDto dto) {
		Client c = service.save( ClientDtoConverter.convertToDto(dto));
		if(c != null) {
			return ClientDtoConverter.convertToDto(c);
		}else {
			return null;
		}
	}

	@Override
	public List<ClientSaveDto> findAll() {
		return ClientDtoConverter.convert(service.findAll());
	}

	@Override
	public ClientSaveDto update(@Valid @RequestBody ClientSaveDto dto) {
		Client c = service.update(ClientDtoConverter.convertToDto(dto));
		if ( c != null) {
			return ClientDtoConverter.convertToDto(c);
		}else {
			return null;
		}
	}

	@Override
	public ClientSaveDto delete(@Valid @RequestBody ClientSaveDto dto) {
		if(service.delete( ClientDtoConverter.convertToDto(dto)) == true) {
			return dto;
		}else {
			return null;
		}
				
	}

}
