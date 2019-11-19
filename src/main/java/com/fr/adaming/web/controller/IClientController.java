package com.fr.adaming.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fr.adaming.web.dto.ClientSaveDto;

@RequestMapping(path = "api/projetimmo/client")
public interface IClientController {

	@PostMapping(path = "/save")
	public ClientSaveDto save(ClientSaveDto dto);

	@GetMapping(path = "/get-all")
	public List<ClientSaveDto> findAll();

	@RequestMapping(path = "/update", method=RequestMethod.PUT)
	public ClientSaveDto update(ClientSaveDto dto);

	@RequestMapping(path = "/get-delete", method = RequestMethod.DELETE)
	public ClientSaveDto delete(ClientSaveDto dto);

}
