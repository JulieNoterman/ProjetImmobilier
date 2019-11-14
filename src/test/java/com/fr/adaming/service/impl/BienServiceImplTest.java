package com.fr.adaming.service.impl;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
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
	
	@Test
	@Sql (statements = "delete from bien where id=15000" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createValidBien_shouldReturnUserWithIdNotNUll() {
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
	
	
}
