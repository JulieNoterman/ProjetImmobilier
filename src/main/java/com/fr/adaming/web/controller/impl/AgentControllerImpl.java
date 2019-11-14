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
import com.fr.adaming.web.dto.LoginAgentDto;

@RestController
@RequestMapping(path = "api/projetimmo/agent")
public class AgentControllerImpl implements IAgentController{

	@Autowired
	@Qualifier("agentServiceImpl")
	private IAgentService service;

	@RequestMapping(path = "/get-all", method = RequestMethod.GET)
	public List<Agent> getAllAgent() {
		return service.findAll();
	}

	@RequestMapping(path = "/get-id/{id}", method = RequestMethod.GET)
	public Optional<Agent> getOneById(Long id) {
		return service.findById(id);
	}

	@RequestMapping(path = "/save", method = RequestMethod.POST)
	public String save(@RequestBody Agent agent) {
		if (service.save(agent) != null) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}
	
	@RequestMapping(path = "/update", method = RequestMethod.PUT)
	public String update(@RequestBody Agent agent) {
		if(service.update(agent) != null) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}
	}

	@RequestMapping(path = "/get-delete/{id}", method = RequestMethod.DELETE)
	public void deleteById(Agent agent) {
		service.delete(agent);
		
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
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
