package com.fr.adaming.web.controller;

import java.util.List;

import com.fr.adaming.entity.Agent;

public interface IAgentController {

	public List<Agent> getAllAgent();
	public Agent getOneById(Long id);
	public String save(Agent agent);
	public Agent update(Agent agent);
	public String deleteById(Long id);
	public Agent login(String Email, String pwd);
	
	
	
}
