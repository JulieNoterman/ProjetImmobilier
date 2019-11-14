package com.fr.adaming.web.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.service.IAgentService;
import com.fr.adaming.service.impl.AgentServiceImpl;
import com.fr.adaming.web.controller.IAgentController;

@RestController
public class AgentControllerImpl implements IAgentController{

	@Autowired
	@Qualifier("agentControllerImpl")
	private IAgentController service;

	@Override
	@RequestMapping(path = "/get-all", method = RequestMethod.GET)
	public List<Agent> getAllAgent() {
		return service.getAllAgent();
	}

	@Override
	@RequestMapping(path = "/{idx}/get-id/", method = RequestMethod.GET)
	public Agent getOneById(@PathVariable(name = "idx") Long id) {
		return service.getOneById(id);
	}

	@Override
	@RequestMapping(path = "/create", method = RequestMethod.POST)
	public String save(@RequestBody Agent agent) {
		if (service.save(agent) != null) {
			return "SUCCESS";
		} else {
			return "FAIL";
		}	
	}

	@Override
	public Agent update(Agent agent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@RequestMapping(path = "/{id}/delete/", method = RequestMethod.DELETE)
	public String deleteById(@PathVariable Long id) {
		service.deleteById(id);
		return "OBJECT DELETED";
	}

	@Override
	public Agent login(String Email, String pwd) {
		// TODO Auto-generated method stub
		return null;
	}
}
