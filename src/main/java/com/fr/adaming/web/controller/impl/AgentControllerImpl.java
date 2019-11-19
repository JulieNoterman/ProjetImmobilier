package com.fr.adaming.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.service.IAgentService;
import com.fr.adaming.web.controller.IAgentController;
import com.fr.adaming.web.dto.AgentSaveDto;
import com.fr.adaming.web.dto.LoginAgentDto;
import com.fr.adaming.web.dto.converter.AgentDtoConverter;

@RestController
public class AgentControllerImpl implements IAgentController{

	@Autowired
	@Qualifier("agentServiceImpl")
	private IAgentService service;

	
	public List<AgentSaveDto> getAllAgent() {
		return AgentDtoConverter.convertAgent(service.findAll());
	}

	
	public AgentSaveDto getOneById(Long id) {
		return AgentDtoConverter.convertToAgent(service.findById(id));
	}

	
	public AgentSaveDto save(@RequestBody AgentSaveDto agentDto) {
		return AgentDtoConverter.convertToAgent(service.save(AgentDtoConverter.convertToDto(agentDto)));
	}
	
	
	public AgentSaveDto update(@RequestBody AgentSaveDto agentDto) {
		return AgentDtoConverter.convertToAgent(service.update(AgentDtoConverter.convertToDto(agentDto)));
	}

	
	public void deleteById(@RequestBody Agent agent) {
		service.delete(agent);
		
	}

	
	public LoginAgentDto login(@RequestBody LoginAgentDto loginAgentDto) {
		System.out.println(loginAgentDto);
		if (service.login(loginAgentDto.getEmail(), loginAgentDto.getPwd()) != null) {
			return loginAgentDto;
		} else {
			return null;
	}


	}

	@Override
	public String login(String email, String pwd) {
		if(service.login(email, pwd) != null) {
			return "SUCCESS";
		}else {
			return "FAIL";
		}
		
	}
}
