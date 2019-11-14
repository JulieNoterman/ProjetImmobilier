package com.fr.adaming.web.controller.impl;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Client;
import com.fr.adaming.service.IClientService;
import com.fr.adaming.web.controller.IClientController;
import com.fr.adaming.web.dto.ClientSaveDto;


@RestController
@RequestMapping(path = "api/projetimmo/client")
public class ClientControllerImpl implements IClientController{
	
	@Autowired
	private IClientService service;


	@Override
	@PostMapping(path = "/save")
	public String save(@Valid @RequestBody ClientSaveDto dto) {
		if(service.save( new Client(dto)) != null) {
			return "SUCCESS";
		}else {
			return "FAIL";
		}
	}

	@Override
	@GetMapping(path = "/findall")
	public List<Client> findAll() {
		return service.findAll();
	}

	@Override
	@RequestMapping(path = "/update", method=RequestMethod.PUT)
	public Client update(@RequestBody ClientSaveDto dto) {
		return service.update(new Client(dto));
	}

	@Override
	public String delete(@RequestBody ClientSaveDto dto) {
		if(service.delete( new Client(dto)) == true) {
			return "SUCCESS";
		}else {
			return "FAIL";
		}
				
	}

}
