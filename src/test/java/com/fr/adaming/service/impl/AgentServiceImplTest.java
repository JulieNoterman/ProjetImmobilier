package com.fr.adaming.service.impl;

import static org.junit.Assert.assertNotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.entity.Client;
import com.fr.adaming.service.IAgentService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AgentServiceImplTest {

	@Autowired
	private IAgentService service;
	
	@Test
//	@Sql(statements = "insert into agent values(45, 'theo@gmail.com', 'theo corneloup', 7744553322, null, 'azerty')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
//	@Sql(statements = "truncate Agent", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createValidAgent_shouldReturnAgentNotNull() {
		
		//preparer input
		Agent a = new Agent();
		
		a.setId(696969L);
		a.setEmail("email@test.fr");
		a.setFullname("billy");
		a.setPwd("aqwzsx");
		a.setTelephone(1122336655L);
		a.setDateRecrutement(LocalDateTime.now());
		
//		List<Client> list = new ArrayList<>();
//		list.add(e)
//		a.setClient(list);
		
		//invoquer la methode
		Agent returnedAgent = service.save(a);
		
		//verification
		assertNotNull(returnedAgent);
		assertNotNull(returnedAgent.getId());
		assertNotNull(returnedAgent.getEmail());
		assertNotNull(returnedAgent.getFullname());
		assertNotNull(returnedAgent.getPwd());
		assertNotNull(returnedAgent.getTelephone());
		assertNotNull(returnedAgent.getDateRecrutement());
		
	}
}
