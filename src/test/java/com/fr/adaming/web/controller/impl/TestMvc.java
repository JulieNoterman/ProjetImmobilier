package com.fr.adaming.web.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class TestMvc {

	@Autowired
	protected MockMvc mvc;
	
	protected ObjectMapper mapper = new ObjectMapper();
	
	
	
}
