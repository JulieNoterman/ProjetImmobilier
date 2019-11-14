package com.fr.adaming.web.controller;

import java.util.List;

import com.fr.adaming.entity.Client;
import com.fr.adaming.web.dto.ClientSaveDto;

public interface IClientController {

	public String save(ClientSaveDto dto);

	public List<Client> findAll();

	public Client update(ClientSaveDto dto);

	public String delete(ClientSaveDto dto);

}
