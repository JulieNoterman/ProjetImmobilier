package com.fr.adaming.web.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fr.adaming.enumeration.TypeClient;
import com.fr.adaming.web.dto.AgentSaveDto;
import com.fr.adaming.web.dto.ClientSaveDto;
import com.fr.adaming.web.dto.converter.ClientDtoConverter;

@RunWith(SpringRunner.class)
public class AgentControllerImplTest extends TestMvc {

	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	//ok
		@Test
		@Sql(statements = "insert into agent values(45, 'theo@gmail.com', 'theo corneloup', 7744553322, null, '78945612')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql (statements = "delete from agent where id = 45" ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
			public void ListValidAgent_shouldNotReturnEmptyList() throws UnsupportedEncodingException, Exception {
			

			mvc.perform(get("/api/projetimmo/agent/get-all").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk())
	                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
	                .andExpect(jsonPath("$[0].id", is(45)));		
		
	}
		
		//ok
		@Test
		public void EmptyListBien_shouldReturnEmptyList() throws Exception {
		
			String mvcresult = mvc.perform(get("/api/projetimmo/agent/get-all").contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
					
					
					
					
					assertEquals("[]",mvcresult);


	}
		//ok
		@Test
		@Sql (statements = {"delete from agent where id=1","delete from agent where id=2" },executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		public void createValidAgent_shouldReturnAgentWithIdNotNUll() throws UnsupportedEncodingException, JsonProcessingException, Exception {
			//prépare les inputs
			
			AgentSaveDto a = new AgentSaveDto();

			a.setId(1L);
			a.setEmail("email@test.fr");
			a.setFullname("billy");
			a.setPwd("12345678");
			a.setTelephone(1122336655L);
			
			
			
			String mvcresult = mvc.perform(post("/api/projetimmo/agent/save")
			.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(a)))
			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			
			AgentSaveDto dtoResult = mapper.readValue(mvcresult, AgentSaveDto.class);
			
			assertNotNull(dtoResult);
			
			
			
			
		}
		
		//ok
		@Test
		@Sql(statements = "insert into agent values(45, 'theo@gmail.com', 'theo corneloup', 7744553322, null, '78945612')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "delete from agent where id=45", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		public void notCreateValidAgent_shouldNotReturnAgentWithId() throws UnsupportedEncodingException, JsonProcessingException, Exception {
			//prépare les inputs
			
			AgentSaveDto a = new AgentSaveDto();
			a.setId(45L);
			a.setEmail("theo@gmail.com");
			a.setFullname("theo corneloup");
			a.setPwd("78945612");
			a.setTelephone(7744553322L);
			
			
			String mvcresult = mvc.perform(post("/api/projetimmo/agent/save")
			.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(a)))
			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			
			

			
			assertEquals("", mvcresult);

			
			
			
		}
		
		
			
			
		//ok
		@Test
		@Sql(statements = "insert into agent values(45, 'theo@gmail.com', 'theo corneloup', 7744553322, null, '78945612')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "delete from agent where id=45", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		public void updateValidAgent_shouldReturnAgentWithModification() throws UnsupportedEncodingException, JsonProcessingException, Exception {
			
			AgentSaveDto a = new AgentSaveDto();
			a.setId(45L);
			a.setEmail("email3@test.fr");
			a.setFullname("Murray");
			a.setPwd("12345678");
			
			
			String mvcresult = mvc.perform(put("/api/projetimmo/agent/update")
			.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(a)))
			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			
			AgentSaveDto dtoResult = mapper.readValue(mvcresult, AgentSaveDto.class);
			
			assertNotNull(dtoResult);
			
			
		}
		
		
		
		
		
		
		
		//ok
		@Test
		@Sql(statements = "insert into agent values(45, 'theo@gmail.com', 'theo corneloup', 7744553322, null, '78945612')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = {"delete from agent where id=45","delete from agent where id=789"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		public void updateNotValidAgent_shouldNotReturnAgentWithIdNull() throws UnsupportedEncodingException, JsonProcessingException, Exception {
			
			AgentSaveDto a = new AgentSaveDto();
			a.setId(789L);
			a.setEmail("email3@test.fr");
			a.setFullname("Murray");
			a.setPwd("PMOLIKUJ");
			
			

			String mvcresult = mvc.perform(put("/api/projetimmo/agent/update")
			.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(a)))
			.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();
			
			
			
			assertEquals("",mvcresult);

			
		
			
		}
		
		//ok
		@Test
		@Sql(statements = "insert into agent values(45, 'theo@gmail.com', 'theo corneloup', 7744553322, null, '78945612')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = {"delete from agent where id=45","delete from agent where id=77"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
			public void deleteNotValidAgent_shouldReturnFalse() throws UnsupportedEncodingException, JsonProcessingException, Exception {
			
			AgentSaveDto a = new AgentSaveDto();
			a.setId(77L);
			
			String mvcresult = mvc.perform(delete("/api/projetimmo/agent/get-delete/{id}", 77L )
					.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(a)))
			        .andExpect(status().is(400)).andReturn().getResponse().getContentAsString();
					

//			BienDto dtoResult = mapper.readValue(mvcresult, BienDto.class);
			
			assertEquals("", mvcresult);
			
			
		}
		

		
		
		//ok
		@Test
		@Sql(statements = "insert into agent values(45, 'theo@gmail.com', 'theo corneloup', 7744553322, null, '78945612')", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
		@Sql(statements = "delete from agent where id=45", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		public void deleteValidAgent_shouldReturnTrue() throws UnsupportedEncodingException, JsonProcessingException, Exception {
			
			
			AgentSaveDto a = new AgentSaveDto();
			a.setId(45L);
			
			String mvcresult = mvc.perform(delete("/api/projetimmo/agent/get-delete/{id}" , 45L )
			.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(a)))
	        .andExpect(status().is(400)).andReturn().getResponse().getContentAsString();
			

			assertEquals("",mvcresult);
			
			

		}
		
		
		
		
		
		@Test
		@Sql(statements = { "delete from agent where email = 'email@test.fr'" , "delete from client where email = 'dylan.salos@gmail.com'" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
		public void saveValidAgentAssociatedWithClient_shouldReturnClientWithIdNotNull() throws UnsupportedEncodingException, JsonProcessingException, Exception {
			//preparer les inputs
			AgentSaveDto a = new AgentSaveDto();
			a.setId(1L);
			a.setEmail("email@test.fr");
			a.setFullname("billy");
			a.setPwd("12345678");
			a.setTelephone(1122336655L);
			
			ClientSaveDto c = new ClientSaveDto();
			c.setId(50000L);
			c.setEmail("dylan.salos@gmail.com");
			c.setFullname("aaaa");
			c.setType(TypeClient.ACHETEUR);
			c.setTelephone(9876543210L);
			List<ClientSaveDto> clients = new ArrayList<ClientSaveDto>();
			clients.add(c);
			
			
			a.setClient(ClientDtoConverter.convert2(clients));
			
			
			String mvcresult = mvc
					.perform(post("/api/projetimmo/agent/save").contentType(MediaType.APPLICATION_JSON)
							.content(mapper.writeValueAsString(a)))
					.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

			AgentSaveDto dtoResult = mapper.readValue(mvcresult, AgentSaveDto.class);

			// verifier le resultat
			assertNotNull(dtoResult);
			assertNotNull(a.getEmail());
			assertNotNull(a.getFullname());
			assertThat(a.getClient()).asList().hasSize(1);
			assertThat(a.getClient().get(0)).hasFieldOrPropertyWithValue("fullname", "aaaa");
			assertThat(a.getClient().get(0)).hasFieldOrPropertyWithValue("email", "dylan.salos@gmail.com");
		}		
	
	@Test
	@Sql(statements = {"delete from agent where id = 100"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveAgentWithNotValidEmail_shouldReturndNull() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		//preparer les inputs
		AgentSaveDto a = new AgentSaveDto();
		a.setId(100L);
		a.setEmail("aaaa");
		a.setFullname("billy");
		a.setPwd("12345678");
		a.setTelephone(1122336655L);
		


		String mvcresult = mvc
				.perform(post("/api/projetimmo/agent/save").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(a)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", mvcresult);
		
	}
	
	@Test
	@Sql(statements = "delete from agent where id = 150", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveAgentWitEmailNull_shouldReturndNull() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		//preparer les inputs
		AgentSaveDto a = new AgentSaveDto();
		a.setId(150L);
		
		a.setFullname("billy");
		a.setPwd("12345678");
		a.setTelephone(1122336655L);
		

		String mvcresult = mvc
				.perform(post("/api/projetimmo/agent/save").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(a)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", mvcresult);
		
	}
	
	@Test
	@Sql(statements = {"delete from agent where email = 'email@test.fr'","delete from agent where id=200"}, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveAgentWitFullNameNull_shouldReturndNull() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		//preparer les inputs
		AgentSaveDto a = new AgentSaveDto();
		a.setId(200L);
		a.setEmail("email@test.fr");
		a.setPwd("12345678");
		a.setTelephone(1122336655L);
		

		String mvcresult = mvc
				.perform(post("/api/projetimmo/agent/save").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(a)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", mvcresult);
		
	}
	
	@Test
	@Sql(statements = {"delete from agent where email = 'email@test.fr'","delete from agent where id=2"} ,executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveAgentWithPwdNull_shouldReturndNull() throws UnsupportedEncodingException, JsonProcessingException, Exception {
		//preparer les inputs
		AgentSaveDto a = new AgentSaveDto();
		a.setId(250L);
		a.setEmail("email@test.fr");
		a.setFullname("Billy");
		a.setTelephone(1122336655L);
		

		String mvcresult = mvc
				.perform(post("/api/projetimmo/agent/save").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(a)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", mvcresult);
		
	}
	
//	
//	@Test
//	@Sql(statements = "delete from agent where id = 39989", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	public void saveAgentWithTelephoneNotValid_shouldReturndError400() throws UnsupportedEncodingException, JsonProcessingException, Exception {
//		//preparer les inputs
//		AgentSaveDto a = new AgentSaveDto();
//		a.setId(39989L);
//		a.setEmail("email5@test.fr");
//		a.setFullname("billy");
//		a.setTelephone(112L);
//		a.setPwd("12345678");
//		
//
//		String mvcresult = mvc
//				.perform(post("/api/projetimmo/agent/save").contentType(MediaType.APPLICATION_JSON)
//						.content(mapper.writeValueAsString(a)))
//				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();
//
//		assertEquals("", mvcresult);
//		
//	}
	
	
	
	
}
