package com.fr.adaming.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
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
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	

	@Test
	@Sql(statements = "delete from client where email = 'email2@adaming.fr'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveValidClient_shouldReturnClientWithIdNotNull() {
		//preparer les inputs
		Client c = new Client();
		c.setEmail("email2@adaming.fr");
		c.setFullname("nom1");
		c.setType(TypeClient.ACHETEUR);
		c.setTelephone(6657956941L);
		
		
		// invoque la methode
		Client returnedClient = service.save(c);
		
		//verifier le resultat
		assertNotNull(returnedClient);
		assertNotNull(returnedClient.getEmail());
		assertNotNull(returnedClient.getFullname());
		assertNotNull(returnedClient.getType());
	}
	
	@Test
	@Sql(statements = "insert into client(id,email, fullname, telephone, type) values(1455454,'email5@adaming.fr', 'nom1', 6657956941, 1);", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from client where id = 1455454", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveNotValidClient_shouldReturndNull() {
		//preparer les inputs
		//meme client que dans le before class
		Client c = new Client();
		c.setId(1455454L);
		c.setEmail("email5@adaming.fr");
		c.setFullname("Admin");
		c.setType(TypeClient.VENDEUR);
		c.setTelephone(6657956941L);
		
		exception.expect(AssertionError.class);
		// invoque la methode
		Client returnedClient = service.save(c);
		
		assertNull(returnedClient);
		
	}
	
	@Test
	public void saveNullClient_shouldReturnNull () {
		//preparer les inputs
		Client c = new Client();
		
		exception.expect(DataIntegrityViolationException.class);
		//invoquer la methode
		Client returnedClient = service.save(c);
		
		assertNull(returnedClient);
	}
	
	
	@Test
	@Sql(statements = "insert into client(id,email, fullname, telephone, type) values(232323,'emailAdmin@adaming.fr', 'nom1', 6657956941, 1);", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from client where id = 232323", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateExistingClient_shouldReturnClientWithIdNotNull() {
		//preparer les inputs
		Client c = new Client();
		c.setId(232323L);
		c.setEmail("emailAdmin@adaming.fr");
		c.setFullname("Admin2");
		c.setType(TypeClient.ACHETEUR);
		
		//invoquer la methode
		Client returnedClient = service.update(c);
		
		assertNotNull(returnedClient);
		assertNotNull(returnedClient.getId());
	}
	
	
	@Test
	public void updateNotExistingClient_shouldReturnNull() {
		//preparer les inputs
		Client c = new Client();
		c.setId(121212L);
		c.setEmail("email@adaming.fr");
		c.setFullname("fullname");
		c.setType(TypeClient.ACHETEUR);
		
		//invoquer la methode
		Client returnedClient = service.update(c);
		
		assertNull(returnedClient);
	}
	
	@Test
	public void updateClientNull_shouldReturnNull() {
		//preparer les inputs
		Client c = new Client();
		
		exception.expect(InvalidDataAccessApiUsageException.class);
		//invoquer la methode
		Client returnedClient = service.update(c);
		
		assertNull(returnedClient);
	}
	
	@Test
	@Sql(statements = "insert into client(id,email, fullname, telephone, type) values(232323,'emailAdmin@adaming.fr', 'nom1', 6657956941, 1);", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from client where id = 232323", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void deleteExistingClient_shouldReturnTrue() {
		//preparer les inputs
		Client c = new Client();
		c.setId(232323L);
		c.setEmail("emailAdmin@adaming.fr");
		c.setFullname("Admin2");
		c.setType(TypeClient.ACHETEUR);
		
		//invoquer la methode
		assertTrue(service.delete(c));
		
		
	}
	
	@Test
	public void deleteNotExistClient_shouldReturnFalse() {
		//preparer les inputs
		Client c = new Client();
		c.setId(232323L);
		c.setEmail("emailAdmin@adaming.fr");
		c.setFullname("Admin2");
		c.setType(TypeClient.ACHETEUR);
		
		//invoquer la methode
		assertFalse(service.delete(c));
	}
	
	
	@Test
	public void ListEmptyClient_shouldReturnEmptyList() {
		List<Client> list = service.findAll();
		assertTrue(list.isEmpty());
	}
	
	
	@Test
	@Sql(statements = "insert into client(id,email, fullname, telephone, type) values(232323,'emailAdmin@adaming.fr', 'nom1', 6657956941, 1);", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from client where id = 232323", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void ListNotEmptyClient_shouldReturnNotEmptyList() {
		List<Client> list = service.findAll();
		assertFalse(list.isEmpty());
		
	}
	

}
