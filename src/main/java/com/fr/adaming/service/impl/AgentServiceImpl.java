package com.fr.adaming.service.impl;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.repository.AgentRepository;
import com.fr.adaming.service.IAgentService;

@Service
public class AgentServiceImpl implements IAgentService {

	@Autowired
	private AgentRepository dao;

	/**
	 *List
	 *
	 *@return List<Agent>
	 */
	@Override
	public List<Agent> findAll() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	/**
	 *Save a given Agent in the DB
	 *
	 *@param Agent - the given entity
	 *@return Create Agent if the given entity is not already in the DB, else return null
	 */
	@Override
	public Agent save(Agent agent) {
		if (dao.exists(Example.of(agent))) {
			return null;
		} else {
			return dao.save(agent);
		}
	}

	/**
	 *Update a given Agent in the DB
	 *
	 *@param Agent - the given entity
	 *@return updated Agent if the given entity already exist in DB, else return null.
	 */
	@Override
	public Agent update(Agent agent) {
		if (findById(agent.getId()) != null) {
			dao.save(agent);
			return agent;
		} else {
			return null;
		}
	}

	/**
	 *Delete a given Agent in the DB
	 *
	 *@param Agent - the given entity
	 *@return delete Agent if the given entity already exist in DB, else return null.
	 */
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

	/**
	 * Connection for Agent
	 * 
	 * @param String email and String pwd
	 * @return Valid connection for an Agent
	 */
	@Override
	public Agent login(String email, String pwd) {
		return dao.findByEmailAndPwd(email, pwd);
	}

	/**
	 * Find a row in DB
	 * 
	 * @param id(Long)
	 * @return Agent
	 */
	@Override
	public Agent findById(Long id) {
		try {
			return dao.findById(id).get();
		} catch (NoSuchElementException e) {
			return null;
		}
	}

	}