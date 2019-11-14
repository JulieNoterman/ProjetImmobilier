package com.fr.adaming.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fr.adaming.entity.Client;
import com.fr.adaming.web.dto.ClientSaveDto;

public interface IClientController {

	@PostMapping(path = "/save")
	public String save(ClientSaveDto dto);

	@GetMapping(path = "/findall")
	public List<Client> findAll();

	@RequestMapping(path = "/update", method=RequestMethod.PUT)
	public Client update(ClientSaveDto dto);

	@RequestMapping(path = "/delete", method = RequestMethod.DELETE)
	public String delete(ClientSaveDto dto);

}
