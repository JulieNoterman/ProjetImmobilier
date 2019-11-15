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
	public String save(@Valid @RequestBody ClientSaveDto dto) {
		if(service.save( ClientDtoConverter.convert(dto)) != null) {
			return "SUCCESS CREATE CLIENT";
		}else {
			return "FAIL CREATE CLIENT";
		}
	}

	@Override
	public List<Client> findAll() {
		return service.findAll();
	}

	@Override
	public Client update(@RequestBody ClientSaveDto dto) {
		return service.update(ClientDtoConverter.convert(dto));
	}

	@Override
	public String delete(@RequestBody ClientSaveDto dto) {
		if(service.delete( ClientDtoConverter.convert(dto)) == true) {
			return "SUCCESS DELETE CLIENT";
		}else {
			return "FAIL DELETE CLIENT";
		}
				
	}

}
