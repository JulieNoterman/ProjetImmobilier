package com.fr.adaming.web.controller.impl;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class HelloWorldControllerTest {

	@Autowired
	private MockMvc mvc;
	
	private ObjectMapper mapper;
	
	@Test
	public void sayHello_ShouldReturnHelloWorld() throws UnsupportedEncodingException, Exception {
		
		
		String mvcresult = mvc.perform(get("/api/hello")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		assertEquals("Coucou", mvcresult);
		
		
	}
}