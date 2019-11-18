package com.fr.adaming.web.controller.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fr.adaming.enumeration.TypeClient;
import com.fr.adaming.web.dto.ClientSaveDto;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
public class HelloWorldControllerTest {

	@Autowired
	private MockMvc mvc;
	
	private ObjectMapper mapper = new ObjectMapper();
	
	
	
	@Test
	public void sayHello_ShouldReturnHelloWorld() throws UnsupportedEncodingException, Exception {
		
		
		
		
		String mvcresult = mvc.perform(get("/api/hello")
		.contentType(MediaType.APPLICATION_JSON))
		.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		
		
		
		assertEquals("Coucou", mvcresult);
		
		
	}
	
	@Test
	public void testMockito_ShouldReturnHelloWorld() throws UnsupportedEncodingException, Exception {
		
		ClientSaveDto client = new ClientSaveDto("a@a.fr","aaaa",TypeClient.ACHETEUR,12345678L);
		
		
		String mvcresult = mvc.perform(get("/api/hello")
		.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(client)))
		.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		ClientSaveDto dtoResult = mapper.readValue(mvcresult, ClientSaveDto.class);
		
		assertNotNull(dtoResult);
		assertEquals("a@a.fr", client.getEmail());
		assertEquals("aaaa", client.getFullname());
		
		
		
	}
}
