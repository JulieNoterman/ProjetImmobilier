package com.fr.adaming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Client;
import com.fr.adaming.repository.ClientRepository;
import com.fr.adaming.service.IClientService;

@Service
public class ClientServiceImpl implements IClientService {
	
	@Autowired
	private ClientRepository dao;

	/**Saves a given client in the database
	 * @param client - the given entity
	 * @return client if the given client didn't exist in the database - else, returns null
	 */
	@Override
	public Client save(Client client) {
		Client c = new Client();
		c.setEmail(client.getEmail());
		c.setFullName(client.getFullName());
		c.setType(client.getType());
		
		
		if(dao.exists(Example.of(c))) {
			//Le client existe dans la BD (FAIL)
			return null;
		}else {
			//Le client n'existe pas (SUCCESS) : enregistrer le client dans la BD et retourner le client
			return dao.save(client);
		}
		
	}

	/** Finds all clients in database
	 * @return List of clients - in case no clients has been inserted, it returns an empty list
	 */
	@Override
	public List<Client> findAll() {
		return dao.findAll();
	}

	/**Updates the values of a given client
	 * @param client - the given entity
	 * @return true if the given client has been modified - else, returns false
	 */
	@Override
	public Client update(Client client) {
		// Chercher client par id
				if(dao.existsById(client.getId())) {
					dao.save(client);
					return client;
				}else {
					System.out.println("DEBUG Le client à modifier n'existe pas ");
					return null;
				}
	}

	
	
	/**Delete a given client of the database
	 *@param client - the given entity
	 *@return nothing 
	 */
	@Override
	public boolean delete(Client client) {
		// TODO Auto-generated method stub
		if(dao.existsById(client.getId()))
		{
			dao.delete(client);
			return true;
		}else {
			System.out.println("DEBUG Le client à supprimer n'existe pas");
			return false;
		}
	}
	

}
