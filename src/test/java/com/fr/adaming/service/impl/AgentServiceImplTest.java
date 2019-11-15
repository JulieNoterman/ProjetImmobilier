package com.fr.adaming.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionSystemException;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.entity.Client;
import com.fr.adaming.enumeration.TypeClient;
import com.fr.adaming.service.IAgentService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AgentServiceImplTest {

	@Autowired
	@Qualifier("agentServiceImpl")
	private IAgentService service;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	@Sql(statements = "delete from agent where id=1", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createValidAgent_shouldReturnNewAgentWithIdNotNull() {

		// preparer input
		Agent a = new Agent();

		a.setId(1L);
		a.setEmail("email@test.fr");
		a.setFullname("billy");
		a.setPwd("12345678");
		a.setTelephone(1122336655L);
		a.setDateRecrutement(LocalDateTime.now());

		
//		//invoquer la methode
		Agent returnedAgent = service.save(a);
		
		// verification
		assertNotNull(returnedAgent);
		assertNotNull(returnedAgent.getId());

	}

	@Test
	@Sql(statements = "insert into agent values(45, 'theo@gmail.com', 'theo corneloup', 7744553322, null, '78945612')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from agent where id=45", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void notCreateValidAgent_shouldNotReturnAgentWithId() {

		Agent a = new Agent();
		a.setId(45L);
		a.setEmail("email2@test.fr");
		a.setFullname("billy");
		a.setPwd("123456789");
		exception.expect(AssertionError.class);

		Agent returnedAgent = service.save(a);

		assertNull(returnedAgent);

	}

	@Test
	@Sql(statements = "insert into agent values(23, 'theo@gmail.com', 'theo corneloup', 7744553322, null, '78945612')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from agent where id=23", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createAgentWithNotValidPwd_shouldNotReturnValidAgent() {

		Agent a = new Agent();
		a.setId(45L);
		a.setEmail("email3@test.fr");
		a.setFullname("billy");
		a.setPwd("123456");
		exception.expect(TransactionSystemException.class);

		Agent returnedAgent = service.save(a);

		assertNull(returnedAgent);
		
	}

	@Test
	@Sql(statements = "insert into agent values(45, 'theo@gmail.com', 'theo corneloup', 7744553322, null, '78945612')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from agent where id=45", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateValidAgent_shouldReturnAgentWithModification() {

		Agent a = new Agent();
		a.setId(45L);
		a.setEmail("email3@test.fr");
		a.setFullname("Murray");
		a.setPwd("12345678");

		Agent returnedAgent = service.update(a);

		assertNotNull(returnedAgent);
		assertNotNull(returnedAgent.getId());
	}

	@Test
	@Sql(statements = "insert into agent values(45, 'theo@gmail.com', 'theo corneloup', 7744553322, null, '78945612')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from agent where id=45", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateNotValidAgent_shouldNotReturnAgentWithIdNull() {

		Agent a = new Agent();
		a.setId(789L);
		a.setEmail("email3@test.fr");
		a.setFullname("Murray");
		a.setPwd("PMOLIKUJ");
		exception.expect(NullPointerException.class);

		Agent returnedAgent = service.update(a);

		assertNull(returnedAgent);
	}

	@Test
	@Sql(statements = "insert into agent values(45, 'theo@gmail.com', 'theo corneloup', 7744553322, null, '78945612')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void deleteValidAgent_shouldReturnTrue() {

		Agent a = new Agent();
		a.setId(45L);
		assertTrue(service.delete(a));
	}

	@Test
	@Sql(statements = "insert into agent values(45, 'theo@gmail.com', 'theo corneloup', 7744553322, null, '78945612')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from agent where id=45", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void deleteNotValidAgent_shouldReturnFalse() {

		Agent a = new Agent();
		a.setId(77L);
		assertFalse(service.delete(a));
	}

	@Test
	public void emptyListAgent_shouldReturnEmptyList() {
		List<Agent> list = service.findAll();
		assertTrue(list.isEmpty());
	}

	@Test
	@Sql(statements = "insert into agent values(45, 'theo93@gmail.com', 'theo corneloup', 7744553322, null, '78945612')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from agent where id=45", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void ListAgent_shouldReturnAgentList() {
		List<Agent> list = service.findAll();
		assertFalse(list.isEmpty());

	}

	
	@Test
	@Sql(statements = "delete from agent where id=45", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	@Sql(statements = "insert into agent values(45, 'theo93@gmail.com', 'theo corneloup', 7744553322, null, '78945612')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	public void testValidLogin_shouldConnectAgent() {
		
		Agent returnedAgent = service.login("theo93@gmail.com", "78945612");
		
		assertNotNull(returnedAgent);
		
	}
	
	@Test
	@Sql(statements = "insert into agent values(45, 'theo93@gmail.com', 'theo corneloup', 7744553322, null, '78945612')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from agent where id=45", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void testNotValidLogin_shouldNotConnectAgent() {
		
		Agent returnedAgent = service.login("theo@gmail.com", "78541236");
		
		assertNull(returnedAgent);
	}
	
	@Test
	@Sql(statements = "delete from agent where email = 'email@test.fr'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveValidAgentAssociatedWithBien_shouldReturnClientWithIdNotNull() {
		//preparer les inputs
		Agent a = new Agent();

		a.setId(1L);
		a.setEmail("email@test.fr");
		a.setFullname("billy");
		a.setPwd("12345678");
		a.setTelephone(1122336655L);
		a.setDateRecrutement(LocalDateTime.now());
		
		Client c = new Client();
		c.setEmail("dylan.salos@gmail.com");
		c.setFullname("aaaa");
		c.setType(TypeClient.ACHETEUR);
		
		List<Client> clients = new ArrayList<Client>();
		
		clients.add(c);
		
		a.setClient(clients);
		
		
		// invoque la methode
		Agent returnedAgent = service.save(a);
		
		//verifier le resultat
		assertNotNull(returnedAgent);
		assertNotNull(returnedAgent.getEmail());
		assertNotNull(returnedAgent.getFullname());
		assertThat(returnedAgent.getClient()).asList().hasSize(1);
		assertThat(returnedAgent.getClient().get(0)).hasFieldOrPropertyWithValue("fullname", "aaaa");
		assertThat(returnedAgent.getClient().get(0)).hasFieldOrPropertyWithValue("email", "dylan.salos@gmail.com");

	}
}