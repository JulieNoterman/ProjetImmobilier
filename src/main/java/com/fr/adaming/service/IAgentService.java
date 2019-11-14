package com.fr.adaming.service;

import java.util.List;

import com.fr.adaming.entity.Agent;

public interface IAgentService {

	public Agent save(Agent agent);
	public List<Agent> findAll();
	public Agent update(Agent agent);
	public Agent delete(Agent agent);
		
	
}
