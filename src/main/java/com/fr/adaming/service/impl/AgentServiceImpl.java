package com.fr.adaming.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.fr.adaming.service.IAgentService;

@Service
public class AgentServiceImpl implements IAgentService {

	@Autowired
	@Qualifier("agentServiceImpl")
	private IAgentService service;
}
