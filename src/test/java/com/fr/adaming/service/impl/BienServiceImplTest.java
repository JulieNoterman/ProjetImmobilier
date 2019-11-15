package com.fr.adaming.service.impl;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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

import com.fr.adaming.entity.Bien;
import com.fr.adaming.service.IBienService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BienServiceImplTest {

	@Autowired
	@Qualifier ("bienServiceImpl")
	private IBienService service;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	@Sql (statements = "delete from bien where id=15000" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createValidBien_shouldReturnBienWithIdNotNUll() {
		//prépare les inputs
		Bien b = new Bien();
		b.setId(15000L);
		b.setPrix(15000);
		b.setVendu(false);
		
		
		//invoque la méthode
		Bien returnedUser = service.save(b);
		
		assertNotNull(returnedUser);
		assertNotNull(returnedUser.getId());
		
	}
	
	@Test
	@Sql (statements = "insert into bien (id,prix,vendu) values (16000,15000,false)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "delete from bien where id=16000" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createNotValidBien_shouldNotReturnBienWithIdNUll() {
		//prépare les inputs
		Bien b = new Bien();
		b.setId(16000L);
		b.setPrix(15000);
		b.setVendu(false);
		exception.expect(NullPointerException.class);
		//invoque la méthode
		Bien returnedUser = service.save(b);
		
		assertNull(returnedUser);
		assertNull(returnedUser.getId());
		
	}
	
	@Test
	@Sql (statements = "insert into bien (id,prix,vendu) values (17000,15000,false)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "delete from bien where id=17000" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateValidBien_shouldReturnBienWithIdNotNull() {
		//prépare les inputs
		Bien b = new Bien();
		b.setId(17000L);
		b.setPrix(700);
		b.setVendu(true);
		
		
		//invoque la méthode
		Bien returnedUser = service.update(b);
		
		assertNotNull(returnedUser);
		assertNotNull(returnedUser.getId());
		
	}
	
	@Test
	@Sql (statements = "insert into bien (id,prix,vendu) values (16000,15000,false)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "delete from bien where id=16000" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateNotValidBien_shouldNotReturnBienWithIdNUll() {
		//prépare les inputs
		Bien b = new Bien();
		b.setId(16000L);
		b.setPrix(15000);
		b.setVendu(false);
		exception.expect(NullPointerException.class);
		//invoque la méthode
		Bien returnedUser = service.save(b);
		
		assertNull(returnedUser);
		assertNull(returnedUser.getId());
		
	}
	
	
}
