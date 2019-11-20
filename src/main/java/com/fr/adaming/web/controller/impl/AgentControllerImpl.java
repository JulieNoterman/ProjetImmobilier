package com.fr.adaming.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.service.IAgentService;
import com.fr.adaming.web.controller.IAgentController;
import com.fr.adaming.web.dto.AgentSaveDto;
import com.fr.adaming.web.dto.LoginAgentDto;
import com.fr.adaming.web.dto.converter.AgentDtoConverter;

@RestController
@CrossOrigin
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

	
	public AgentSaveDto save( AgentSaveDto agentDto) {
		Agent a = service.save(AgentDtoConverter.convertToDto(agentDto));
		if (a != null) {
			return AgentDtoConverter.convertToAgent(a);
		}
		return null;
	}
	
	
	public AgentSaveDto update( AgentSaveDto agentDto) {
		Agent a = service.update(AgentDtoConverter.convertToDto(agentDto));
		if (a != null) {
			return AgentDtoConverter.convertToAgent(a);
		}
		return null;
	}

	
	public void deleteById( AgentSaveDto agentDto) {
		service.delete(AgentDtoConverter.convertToDto(agentDto));
		
	}

	
	public LoginAgentDto login(LoginAgentDto loginAgentDto) {
		System.out.println(loginAgentDto);
		if (service.login(loginAgentDto.getEmail(), loginAgentDto.getPwd()) != null) {
			return loginAgentDto;
		} else {
			return null;
	}


	}

	
}
