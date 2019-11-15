package com.fr.adaming.service;

import java.util.List;
import java.util.Optional;

import com.fr.adaming.entity.Agent;

public interface IAgentService {

	public List<Agent> findAll();
	public Agent save(Agent agent);
	public Agent update(Agent agent);
	public boolean delete(Agent agent);
	public Agent login(String email, String pwd);
	public Agent findById(Long id);
		
	
}
