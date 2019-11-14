package com.fr.adaming.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.service.IAgentService;

@Service
public class AgentServiceImpl implements IAgentService {

	@Autowired
	@Qualifier("agentServiceImpl")
	private IAgentService service;

	@Override
	public Agent save(Agent agent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Agent> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Agent update(Agent agent) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Agent delete(Agent agent) {
		// TODO Auto-generated method stub
		return null;
	}
}
