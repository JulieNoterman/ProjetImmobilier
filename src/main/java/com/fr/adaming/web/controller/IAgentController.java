package com.fr.adaming.web.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.web.dto.AgentSaveDto;
import com.fr.adaming.web.dto.LoginAgentDto;

@RequestMapping(path = "api/projetimmo/agent")
@CrossOrigin
public interface IAgentController {

	@RequestMapping(path = "/get-all", method = RequestMethod.GET)
	public List<AgentSaveDto> getAllAgent();
	
	@RequestMapping(path = "/get-id/{id}", method = RequestMethod.GET)
	public AgentSaveDto getOneById(Long id);
	
	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public AgentSaveDto save(@RequestBody @Valid AgentSaveDto agentDto);
	
	@RequestMapping(path = "/update", method = RequestMethod.PUT)
	public AgentSaveDto update(@RequestBody @Valid AgentSaveDto agentDto);
	
	@RequestMapping(path = "/get-delete/{id}", method = RequestMethod.DELETE)
	public void deleteById(@RequestBody @Valid AgentSaveDto agentDto);
	
	public LoginAgentDto login(@RequestBody LoginAgentDto loginAgentDto);

	
	
	
}
