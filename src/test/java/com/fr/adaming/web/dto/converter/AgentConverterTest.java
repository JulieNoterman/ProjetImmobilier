package com.fr.adaming.web.dto.converter;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.fr.adaming.entity.Agent;

public class AgentConverterTest {
	
	@Test
	public void convertNulltoDto_shouldReturnNull() {
		assertNull(AgentDtoConverter.convertToDto(null));
	}
	
	@Test
	public void convertNulltoEntity_shouldReturnNull() {
		assertNull(AgentDtoConverter.convertToAgent(null));
	}
	
	@Test
	public void convertListAgentNullToListDtos_shouldReturnNull() {
		assertNull(AgentDtoConverter.convertAgent(null));
	}
	
	@Test
	public void convertListEntitiesWithNullObjectToListDtos_shouldReturnNull() {
		List<Agent> list = new ArrayList<Agent>();
		assertNotNull(AgentDtoConverter.convertAgent(list));
	}

}
