package com.fr.adaming.service.impl;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.fr.adaming.service.IBienService;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

	@Autowired
	@Qualifier ("bieServiceImpl")
	private IBienService service;
	
	@Test
	public void exemple() {
		boolean x = true;
		assertTrue(x);
	}
}
