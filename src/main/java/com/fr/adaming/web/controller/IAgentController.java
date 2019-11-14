package com.fr.adaming.web.controller;

import java.util.List;
import java.util.Optional;

import com.fr.adaming.entity.Agent;

public interface IAgentController {

	public List<Agent> getAllAgent();
	public Optional<Agent> getOneById(Long id);
	public String save(Agent agent);
	public String update(Agent agent);
	public void deleteById(Agent agent);
	public String login(String Email, String pwd);

	
	
	
}
