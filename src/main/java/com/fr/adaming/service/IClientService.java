package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Client;

public interface IClientService {
	
	
	public Client save(Client client);
	
	public List<Client> findAll();
	
	public Client update(Client client);
	
	public boolean delete(Client client);

}
