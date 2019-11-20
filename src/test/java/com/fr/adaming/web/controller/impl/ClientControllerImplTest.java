package com.fr.adaming.web.controller.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
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
import com.fr.adaming.entity.Client;
import com.fr.adaming.enumeration.TypeClient;
import com.fr.adaming.web.dto.AgentSaveDto;
import com.fr.adaming.web.dto.BienDto;
import com.fr.adaming.web.dto.ClientSaveDto;
import com.fr.adaming.web.dto.converter.AgentDtoConverter;
import com.fr.adaming.web.dto.converter.BienDtoConverter;

@RunWith(SpringRunner.class)
public class ClientControllerImplTest extends TestMvc {

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	@Sql(statements = "delete from client where email = 'a@a.fr'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveValidClient_shouldReturnClientWithIdNotNull()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {

		ClientSaveDto clientDto = new ClientSaveDto();
		clientDto.setId(1L);
		clientDto.setEmail("a@a.fr");
		clientDto.setFullname("aaaa");
		clientDto.setType(TypeClient.ACHETEUR);
		clientDto.setTelephone(1234567890L);

		String mvcresult = mvc
				.perform(post("/api/projetimmo/client/save").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(clientDto)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		ClientSaveDto dtoResult = mapper.readValue(mvcresult, ClientSaveDto.class);

		assertNotNull(dtoResult);
		assertEquals("a@a.fr", clientDto.getEmail());
		assertEquals("aaaa", clientDto.getFullname());

	}

	@Test
	@Sql(statements = { "delete from client where email = 'email2@adaming.fr'",
			"delete from agent where email = 'emailAgentForTest@shouldBeDeleted.fr'" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveValidClientAssociatedWithAgent_shouldReturnClientWithIdNotNull()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// preparer les inputs
		ClientSaveDto c = new ClientSaveDto();
		c.setId(14578L);
		c.setEmail("email2@adaming.fr");
		c.setFullname("nom1");
		c.setType(TypeClient.ACHETEUR);
		c.setTelephone(6657956941L);
		AgentSaveDto a = new AgentSaveDto();
		a.setEmail("emailAgentForTest@shouldBeDeleted.fr");
		a.setFullname("fullNameForTest");
		a.setPwd("passwor4Test");
		a.setTelephone(9876543210L);
		c.setAgent(AgentDtoConverter.convertToDto(a));

		String mvcresult = mvc
				.perform(post("/api/projetimmo/client/save").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(c)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		ClientSaveDto dtoResult = mapper.readValue(mvcresult, ClientSaveDto.class);

		// verifier le resultat
		assertNotNull(dtoResult);
		assertNotNull(c.getEmail());
		assertNotNull(c.getFullname());
		assertNotNull(c.getType());
		assertNotNull(c.getAgent());
		assertThat(c.getAgent()).hasFieldOrPropertyWithValue("fullname", "fullNameForTest");
		assertThat(c.getAgent()).hasFieldOrPropertyWithValue("email", "emailAgentForTest@shouldBeDeleted.fr");
		assertThat(c.getAgent()).hasFieldOrPropertyWithValue("pwd", "passwor4Test");
		assertThat(c.getAgent()).hasFieldOrPropertyWithValue("telephone", 9876543210L);
	}

	@Test
	@Sql(statements = { "delete from client where email = 'email2@adaming.fr'",
			"truncate table bien" }, executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveValidClientAssociatedWithBien_shouldReturnClientWithIdNotNull()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// preparer les inputs
		ClientSaveDto c = new ClientSaveDto();
		c.setId(45687L);
		c.setEmail("email2@adaming.fr");
		c.setFullname("nom1");
		c.setType(TypeClient.ACHETEUR);
		c.setTelephone(6657956941L);

		BienDto b = new BienDto();
		b.setPrix(10000);
		b.setVendu(true);
		List<BienDto> biens = new ArrayList<BienDto>();
		biens.add(b);

		c.setBien(BienDtoConverter.convertDto(biens));

		String mvcresult = mvc
				.perform(post("/api/projetimmo/client/save").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(c)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		ClientSaveDto dtoResult = mapper.readValue(mvcresult, ClientSaveDto.class);

		// verifier le resultat
		assertNotNull(dtoResult);
		assertNotNull(c.getEmail());
		assertNotNull(c.getFullname());
		assertNotNull(c.getType());
		assertNotNull(c.getBien());
		assertThat(c.getBien()).asList().hasSize(1);
		assertThat(c.getBien().get(0)).hasFieldOrPropertyWithValue("prix", 10000);
		assertThat(c.getBien().get(0)).hasFieldOrPropertyWithValue("vendu", true);

	}

	@Test
	@Sql(statements = "insert into client(id,email, fullname, telephone, type) values(1455454,'email5@adaming.fr', 'nom1', 6657956941, 1);", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from client where id = 1455454", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveNotValidClient_shouldReturndNull()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// preparer les inputs
		// inserer un client qui existe deja
		ClientSaveDto c = new ClientSaveDto();
		c.setId(1455454L);
		c.setEmail("email5@adaming.fr");
		c.setFullname("nom1");
		c.setType(TypeClient.ACHETEUR);
		c.setTelephone(6657956941L);

		exception.expect(AssertionError.class);

		String mvcresult = mvc
				.perform(post("/api/projetimmo/client/save").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(c)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		ClientSaveDto dtoResult = mapper.readValue(mvcresult, ClientSaveDto.class);

		assertNull(dtoResult);

	}

	@Test
	@Sql(statements = "delete from client where id = 1455454", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveClientWithNotValidEmail_shouldReturndNull()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// preparer les inputs
		ClientSaveDto c = new ClientSaveDto();
		c.setId(1455454L);
		c.setEmail("abcde");
		c.setFullname("nom1");
		c.setType(TypeClient.ACHETEUR);
		c.setTelephone(6657956941L);


		String mvcresult = mvc
				.perform(post("/api/projetimmo/client/save").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(c)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", mvcresult);

	}

	@Test
	@Sql(statements = "delete from client where id = 1455454", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveClientWitEmailNull_shouldReturndNull()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// preparer les inputs
		ClientSaveDto c = new ClientSaveDto();
		c.setId(1455454L);
		c.setEmail(null);
		c.setFullname("nom1");
		c.setType(TypeClient.ACHETEUR);
		c.setTelephone(6657956941L);

		String mvcresult = mvc
				.perform(post("/api/projetimmo/client/save").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(c)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", mvcresult);

	}

	@Test
	@Sql(statements = "delete from client where id = 1455454", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveClientWithFullnameNull_shouldReturndNull()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// preparer les inputs
		ClientSaveDto c = new ClientSaveDto();
		c.setId(1455454L);
		c.setEmail("email@test.fr");
		c.setFullname(null);
		c.setType(TypeClient.ACHETEUR);
		c.setTelephone(6657956941L);

		String mvcresult = mvc
				.perform(post("/api/projetimmo/client/save").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(c)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", mvcresult);

	}

	@Test
	@Sql(statements = "delete from client where id = 1455454", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void saveClientWithTypeNull_shouldReturndNull()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// preparer les inputs
		ClientSaveDto c = new ClientSaveDto();
		c.setId(1455454L);
		c.setEmail("email@test.fr");
		c.setFullname("nom1");
		c.setType(null);
		c.setTelephone(6657956941L);

		String mvcresult = mvc
				.perform(post("/api/projetimmo/client/save").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(c)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", mvcresult);

	}

//	@Test
//	@Sql(statements = "delete from client where id = 1455454", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
//	public void saveClientWithTelNotValid_shouldReturndNull()
//			throws UnsupportedEncodingException, JsonProcessingException, Exception {
//		// preparer les inputs
//		ClientSaveDto c = new ClientSaveDto();
//		c.setId(1455454L);
//		c.setEmail("email@test.fr");
//		c.setFullname("nom1");
//		c.setType(TypeClient.ACHETEUR);
//		c.setTelephone(66L);
//
//		String mvcresult = mvc
//				.perform(post("/api/projetimmo/client/save").contentType(MediaType.APPLICATION_JSON)
//						.content(mapper.writeValueAsString(c)))
//				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();
//
//		assertEquals("", mvcresult);
//
//	}
	

	@Test
	public void saveNullClient_shouldReturnNull()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// preparer les inputs
		ClientSaveDto c = new ClientSaveDto();

		String mvcresult = mvc
				.perform(post("/api/projetimmo/client/save").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(c)))
				.andExpect(status().is(400)).andReturn().getResponse().getContentAsString();

		assertEquals("", mvcresult);

	}

	@Test
	@Sql(statements = "insert into client(id,email, fullname, telephone, type) values(232323,'emailAdmin@adaming.fr', 'nom1', 6657956941, 1);", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from client where email = 'emailAdmin@adaming.fr'", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void deleteExistingClient_shouldReturnTrue()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// preparer les inputs
		ClientSaveDto c = new ClientSaveDto();
		c.setId(232323L);
		System.out.println("L'id est le suivant : " + c.getId());
		c.setEmail("emailAdmin@adaming.fr");
		c.setFullname("nom1");
		c.setType(TypeClient.VENDEUR);
		c.setTelephone(6657956941L);

		String mvcresult = mvc
				.perform(delete("/api/projetimmo/client/get-delete").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(c)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		ClientSaveDto dtoResult = mapper.readValue(mvcresult, ClientSaveDto.class);

		// invoquer la methode
		assertNotNull(dtoResult);

	}

	@Test
	public void deleteNotExistClient_shouldReturnFalse()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// preparer les inputs
		Client c = new Client();
		c.setId(232323L);
		c.setEmail("emailAdmin@adaming.fr");
		c.setFullname("Admin2");
		c.setType(TypeClient.ACHETEUR);

		String mvcresult = mvc
				.perform(delete("/api/projetimmo/client/get-delete").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(c)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("", mvcresult);
	}

	@Test
	@Sql(statements = "insert into client(id,email, fullname, telephone, type) values(78946251,'emailAdmin@adaming.fr', 'nom1', 6657956941, 1);", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from client where id = 78946251 ", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void updateExistingClient_shouldReturnClientWithIdNotNull()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// preparer les inputs
		ClientSaveDto c = new ClientSaveDto();
		c.setId(78946251L);
		System.out.println("L'id update est le suivant :" + c.getId());
		c.setEmail("emailAdmin@adaming.fr");
		c.setFullname("Admin2");
		c.setType(TypeClient.ACHETEUR);
		c.setTelephone(1234567890L);

		String mvcresult = mvc
				.perform(put("/api/projetimmo/client/update").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(c)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		ClientSaveDto dtoResult = mapper.readValue(mvcresult, ClientSaveDto.class);

		assertNotNull(dtoResult);
		assertEquals("Admin2", dtoResult.getFullname());

	}

	@Test
	public void updateNotExistingClient_shouldReturnNull()
			throws UnsupportedEncodingException, JsonProcessingException, Exception {
		// preparer les inputs
		Client c = new Client();
		c.setId(121212L);
		c.setEmail("email@adaming.fr");
		c.setFullname("fullname");
		c.setType(TypeClient.ACHETEUR);

		String mvcresult = mvc
				.perform(put("/api/projetimmo/client/update").contentType(MediaType.APPLICATION_JSON)
						.content(mapper.writeValueAsString(c)))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("", mvcresult);
	}

	@Test
	public void ListEmptyClient_shouldReturnEmptyList() throws UnsupportedEncodingException, Exception {
		String mvcresult = mvc.perform(get("/api/projetimmo/client/get-all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andReturn().getResponse().getContentAsString();

		assertEquals("[]", mvcresult);
	}

	@Test
	@Sql(statements = "insert into client(id,email, fullname, telephone, type) values(232323,'emailAdmin@adaming.fr', 'nom1', 6657956941, 1);", executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
	@Sql(statements = "delete from client where id = 232323", executionPhase = ExecutionPhase.AFTER_TEST_METHOD)
	public void ListNotEmptyClient_shouldReturnNotEmptyList() throws Exception {

		mvc.perform(get("/api/projetimmo/client/get-all").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[0].id", is(232323)));

	}

}
