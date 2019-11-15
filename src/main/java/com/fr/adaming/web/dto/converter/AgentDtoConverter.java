package com.fr.adaming.web.dto.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.entity.Agent;
import com.fr.adaming.web.dto.AgentSaveDto;

public class AgentDtoConverter {

		public static Agent convertToDto(AgentSaveDto agentDto) {
			Agent agent = new Agent();
			agent.setId(agentDto.getId());
			agent.setEmail(agentDto.getEmail());
			agent.setFullname(agentDto.getFullname());
			agent.setPwd(agentDto.getPwd());
			agent.setTelephone(agentDto.getTelephone());
			agent.setDateRecrutement(agentDto.getDateRecrutement());
			return agent;
		}

		public static AgentSaveDto convertToAgent(Agent agent) {
			AgentSaveDto agentDto = new AgentSaveDto();
			agentDto.setId(agent.getId());
			agentDto.setEmail(agent.getEmail());
			agentDto.setFullname(agent.getFullname());
			agentDto.setPwd(agent.getPwd());
			agentDto.setTelephone(agent.getTelephone());
			agentDto.setDateRecrutement(agent.getDateRecrutement());
			return agentDto;
		}
		
		public static List<Agent> convertDto(List<AgentSaveDto> AgentsDto) {
			List<Agent> listAgents = new ArrayList<>();
			for(AgentSaveDto dto : AgentsDto) {
				listAgents.add(AgentDtoConverter.convertToDto(dto));
			}
			return listAgents;
		}
		
		public static List<AgentSaveDto> convertAgent(List<Agent> Agents) {
			List<AgentSaveDto> listDtos = new ArrayList<>();
			for(Agent agent : Agents) {
				listDtos.add(AgentDtoConverter.convertToAgent(agent));
			}
			return listDtos;
		}
}
