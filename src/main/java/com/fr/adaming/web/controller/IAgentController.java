package com.fr.adaming.web.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.web.dto.AgentSaveDto;

public interface IAgentController {

	@RequestMapping(path = "/get-all", method = RequestMethod.GET)
	public List<AgentSaveDto> getAllAgent();
	
	@RequestMapping(path = "/get-id/{id}", method = RequestMethod.GET)
	public AgentSaveDto getOneById(Long id);
	
	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public AgentSaveDto save(AgentSaveDto agentDto);
	
	@RequestMapping(path = "/update", method = RequestMethod.PUT)
	public AgentSaveDto update(AgentSaveDto agentDto);
	
	@RequestMapping(path = "/get-delete/{id}", method = RequestMethod.DELETE)
	public void deleteById(Agent agent);
	
	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(String Email, String pwd);

	
	
	
}
