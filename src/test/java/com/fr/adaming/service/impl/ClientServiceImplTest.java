package com.fr.adaming.service.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.entity.Client;
import com.fr.adaming.enumeration.TypeClient;
import com.fr.adaming.service.IClientService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientServiceImplTest {
	
	@Autowired
	private IClientService service;
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("Before Class Method");
		Client c = new Client();
		
	}
	
	@Test
	//@Sql (statements = {"insert into client(id,email, fullname, type) values(1,\"admin@adaming.fr\", \"nom1\", 1);"}, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD )
	public void saveValidClient_shouldReturnClientWithIdNotNull() {
		//preparer les inputs
		Client c = new Client();
		c.setEmail("email2@adaming.fr");
		c.setFullname("nom1");
		c.setType(TypeClient.ACHETEUR);
		
		
		// invoque la methode
		Client returnedClient = service.save(c);
		
		//verifier le resultat
		assertNotNull(returnedClient);
		assertNotNull(returnedClient.getEmail());
		assertNotNull(returnedClient.getFullname());
		assertNotNull(returnedClient.getType());
	}
	

}
