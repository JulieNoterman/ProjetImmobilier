package com.fr.adaming.service.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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
	public void EmptyListBien_shouldReturnEmptyList() {
	
	List<Bien> list = service.findAll();
	assertTrue(list.isEmpty());

}
	
	
	@Test
	@Sql (statements = "insert into bien (id,prix,vendu) values (21000,15000,false)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "truncate table bien" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		public void ListValidBien_shouldNotReturnEmptyList() {
		
		List<Bien> list = service.findAll();
		assertFalse(list.isEmpty());
	
}
	
	@Test
	@Sql (statements = "truncate table bien" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	@Sql (statements = "truncate table bien" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	@Sql (statements = "truncate table bien" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
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
	@Sql (statements = "insert into bien (id,prix,vendu) values (18000,15000,false)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "truncate table bien" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateNotValidBien_shouldNotReturnBienWithIdNUll() {
		//prépare les inputs
		Bien b = new Bien();
		b.setId(18500L);
		b.setPrix(700);
		b.setVendu(true);
		exception.expect(NullPointerException.class);
		//invoque la méthode
		Bien returnedUser = service.update(b);
		
		assertNull(returnedUser);
		assertNull(returnedUser.getId());
		
	}
	
	@Test
	@Sql (statements = "insert into bien (id,prix,vendu) values (19000,15000,false)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		public void deleteValidBien_shouldReturnTrue() {
		
		Bien b = new Bien();
		b.setId(19000L);
		assertTrue(service.delete(b));
	}
	
	@Test
	@Sql (statements = "insert into bien (id,prix,vendu) values (20000,15000,false)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "delete from bien where id=20000" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		public void deleteNotValidBien_shouldReturnTrue() {
		
		Bien b = new Bien();
		b.setId(20500L);
		assertFalse(service.delete(b));
	}
	
	
	
	
	
	
}
