package com.fr.adaming.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.repository.AgentRepository;
import com.fr.adaming.service.IAgentService;

@Service
public class AgentServiceImpl implements IAgentService {

	@Autowired
	private AgentRepository dao;

	@Override
	public List<Agent> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	
	
	public Agent save(Agent agent) {
		if (dao.exists(Example.of(agent))) {
			return null;
		} else {
			return dao.save(agent);
		}
	}

	@Override
	public Agent update(Agent agent) {
		if (findById(agent.getId()) != null) {
			dao.save(agent);
			return agent;
		} else {
			return null;
		}
	}

	@Override
	public boolean delete(Agent agent) {
		// TODO Auto-generated method stub
		if (findById(agent.getId()) != null) {
			dao.delete(agent);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Agent login(String email, String pwd) {
		return dao.findByEmailAndPwd(email, pwd);
	}

	@Override
	public Agent findById(Long id) {
		try {
			return dao.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	}