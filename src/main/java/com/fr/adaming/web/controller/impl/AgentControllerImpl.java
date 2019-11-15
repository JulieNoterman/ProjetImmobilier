package com.fr.adaming.web.controller.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.service.IAgentService;
import com.fr.adaming.web.controller.IAgentController;
import com.fr.adaming.web.dto.AgentSaveDto;
import com.fr.adaming.web.dto.LoginAgentDto;
import com.fr.adaming.web.dto.converter.AgentDtoConverter;

@RestController
@RequestMapping(path = "api/projetimmo/agent")
public class AgentControllerImpl implements IAgentController{

	@Autowired
	@Qualifier("agentServiceImpl")
	private IAgentService service;

	
	public List<Agent> getAllAgent() {
		return service.findAll();
	}

	
	public Agent getOneById(Long id) {
		return service.findById(id);
	}

	
	public String save(@RequestBody AgentSaveDto agentDto) {
		if (service.save(AgentDtoConverter.convertToDto(agentDto)) != null) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}
	
	
	public String update(@RequestBody AgentSaveDto agentDto) {
		if(service.update(AgentDtoConverter.convertToDto(agentDto)) != null) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}

	
	public void deleteById(@RequestBody Agent agent) {
		service.delete(agent);
		
	}

	
	public String login(@RequestBody LoginAgentDto loginAgentDto) {
		System.out.println(loginAgentDto);
		if (service.login(loginAgentDto.getEmail(), loginAgentDto.getPwd()) != null) {
			return "SUCCESS";
		} else {
			return "FAIL";
	}


	}

	@Override
	public String login(String Email, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}
}
