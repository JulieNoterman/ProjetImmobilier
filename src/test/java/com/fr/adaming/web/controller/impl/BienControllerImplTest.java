package com.fr.adaming.web.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.Collection;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.transaction.TransactionSystemException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fr.adaming.entity.Bien;
import com.fr.adaming.entity.Client;
import com.fr.adaming.enumeration.TypeClient;
import com.fr.adaming.web.dto.BienDto;
import com.fr.adaming.web.dto.ClientSaveDto;
import com.fr.adaming.web.dto.converter.ClientDtoConverter;

@RunWith(SpringRunner.class)
public class BienControllerImplTest extends TestMvc {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	
	@Test
	@Sql (statements = "insert into bien (id,prix,vendu) values (21000,15000,false)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "delete from bien where id = 21000" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		public void ListValidBien_shouldNotReturnEmptyList() throws UnsupportedEncodingException, Exception {
		

		mvc.perform(get("/api/projetimmo/bien/get-all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", is(21000)));		
	
}
	
	@Test
	public void EmptyListBien_shouldReturnEmptyList() throws Exception {
	
		String mvcresult = mvc.perform(get("/api/projetimmo/bien/get-all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
				
				
				
				
				assertEquals("[]",mvcresult);


}
	
	@Test
	@Sql (statements = "delete from bien where id=10000" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createValidBien_shouldReturnBienWithIdNotNUll() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		//prépare les inputs
		
		BienDto bien = new BienDto();
		bien.setId(10000L);
		bien.setPrix(200);
		bien.setVendu(true);
		
		
		String mvcresult = mvc.perform(post("/api/projetimmo/bien/save")
		.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(bien)))
		.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		BienDto dtoResult = mapper.readValue(mvcresult, BienDto.class);
		
		assertNotNull(dtoResult);
		assertEquals(200, bien.getPrix());
		assertEquals(true, bien.isVendu());
		
		
		
	}
	
	
	@Test
	@Sql (statements = "insert into bien (id,prix,vendu) values (11000,200,true)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "delete from bien where id=11000" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createExistingBien_shouldReturnBienWithIdNotNUll() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		//prépare les inputs
		
		BienDto bien = new BienDto();
		bien.setId(11000L);
		bien.setPrix(200);
		bien.setVendu(true);
		
		
		String mvcresult = mvc.perform(post("/api/projetimmo/bien/save")
		.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(bien)))
		.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		

		
		assertEquals("", mvcresult);

		
		
		
	}
	
	
		
		
	
	@Test
	@Sql (statements = "insert into bien (id,prix,vendu) values (17000,15000,false)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "truncate table bien" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateValidBien_shouldReturnBienWithIdNotNull() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		
		BienDto bien = new BienDto();
		bien.setId(17000L);
		bien.setPrix(10000);
		bien.setVendu(true);
		
		
		String mvcresult = mvc.perform(put("/api/projetimmo/bien/update")
		.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(bien)))
		.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		BienDto dtoResult = mapper.readValue(mvcresult, BienDto.class);
		
		assertNotNull(dtoResult);
		assertEquals(10000, bien.getPrix());
		assertEquals(true, bien.isVendu());
		
	}
	
	
	
	
	
	
	
	
	@Test
	@Sql (statements = "insert into bien (id,prix,vendu) values (18000,15000,false)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "truncate table bien" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateNotValidBien_shouldNotReturnBienWithIdNUll() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		
		Bien b = new Bien();
		b.setId(18500L);
		b.setPrix(700);
		b.setVendu(true);
		
		

		String mvcresult = mvc.perform(put("/api/projetimmo/bien/update")
		.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(b)))
		.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		
		
		
		assertEquals("",mvcresult);

		
	
		
	}
	
	
	@Test
	@Sql (statements = "insert into bien (id,prix,vendu) values (20000,15000,false)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "delete from bien where id=20000" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		public void deleteNotValidBien_shouldReturnTrue() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		
		Bien b = new Bien();
		b.setId(20500L);
		
		String mvcresult = mvc.perform(delete("/api/projetimmo/bien/get-delete/{id}", 20500L )
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(b)))
		        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
				

//		BienDto dtoResult = mapper.readValue(mvcresult, BienDto.class);
		
		assertEquals("", mvcresult);
		
		
	}
	
	@Test
	@Sql (statements = "truncate table bien" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createValidBienWithWrongPrix_shouldReturnConstraintViolationException() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		
		BienDto b = new BienDto();
		b.setId(15000L);
		b.setPrix(-15000);
		b.setVendu(false);
		
		String mvcresult = mvc.perform(post("/api/projetimmo/bien/save")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(b)))
				
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();
		
		
				
				assertEquals("",mvcresult);

		
		

		
		
		
	}
	
	@Test
	@Sql (statements = "truncate table bien" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void createValidBienWithMinPrix_shouldReturnBienWithIdNotNUll() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		
		BienDto b = new BienDto();
		b.setId(15000L);
		b.setPrix(1);
		b.setVendu(false);
		
		
		
		String mvcresult = mvc.perform(post("/api/projetimmo/bien/save")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(b)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
				
				BienDto dtoResult = mapper.readValue(mvcresult, BienDto.class);
				
				assertNotNull(dtoResult);
		
	}
	
	
	
	@Test
	@Sql (statements = "insert into bien (id,prix,vendu) values (19000,15000,false)" ,executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql (statements = "truncate table bien" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		public void deleteValidBien_shouldReturnTrue() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		
		
		BienDto bien = new BienDto();
		bien.setId(19000L);
		
		String mvcresult = mvc.perform(delete("/api/projetimmo/bien/get-delete/{id}" , 19000L )
		.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(bien)))
        .andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
		

		assertEquals("",mvcresult);
		
		

	}
	
	
	
	
	
	@Test
	@Sql(statements = {"truncate table bien", "delete from client where email = 'dylan.salos@gmail.com'" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveValidBienAssociatedWithClient_shouldReturnClientWithIdNotNull() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		//preparer les inputs
		BienDto b = new BienDto();
		b.setId(15000L);
		b.setPrix(15000);
		b.setVendu(false);
		ClientSaveDto c = new ClientSaveDto();
		c.setEmail("dylan.salos@gmail.com");
		c.setFullname("bbbb");
		c.setType(TypeClient.VENDEUR);
		
		b.setClient(ClientDtoConverter.convertToDto(c));
		
		
		// invoque la methode
		String mvcresult = mvc.perform(post("/api/projetimmo/bien/save")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(b)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
				
				BienDto dtoResult = mapper.readValue(mvcresult, BienDto.class);
				
				assertNotNull(dtoResult);
		
		//verifier le resultat
		assertNotNull(dtoResult);
		assertFalse(b.isVendu());
		assertNotNull(dtoResult.getPrix());
		assertNotNull(dtoResult.getClient());
		assertThat(dtoResult.getClient()).hasFieldOrPropertyWithValue("fullname", "bbbb");
		assertThat(dtoResult.getClient()).hasFieldOrPropertyWithValue("email", "dylan.salos@gmail.com");
		
		
		assertNotNull(dtoResult.getClient().getId());
	}
	
	
	
}
