package com.fr.adaming.web.dto.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.entity.Client;
import com.fr.adaming.web.dto.ClientSaveDto;

public class ClientDtoConverter {
	
	public static Client convertToDto(ClientSaveDto dto) {
		Client client = new Client();
		if (dto == null) {
			return null;
		}else {
			client.setId(dto.getId());
			client.setEmail(dto.getEmail());
			client.setFullname(dto.getFullname());
			client.setTelephone(dto.getTelephone());
			client.setType(dto.getType());
			return client;
		}
		
	}
	
	public static ClientSaveDto convertToDto(Client client) {
		ClientSaveDto dto = new ClientSaveDto();
		if (client == null) {
			return null ;
		} else {
			dto.setId(client.getId());
			dto.setEmail(client.getEmail());
			dto.setFullname(client.getFullname());
			dto.setTelephone(client.getTelephone());
			dto.setType(client.getType());
			return dto;
		}
		
	}
	
	
	
	public static List<Client> convert2(List<ClientSaveDto> dtos){
		List<Client> listClients = new ArrayList<Client>();
		if (dtos == null) {
			return null;
		} else {
			
			for(ClientSaveDto dto : dtos) {
				listClients.add(ClientDtoConverter.convertToDto(dto));
			}
			return listClients;
		}
	
		
	}
	
	public static List<ClientSaveDto> convert(List<Client> clients){
		List<ClientSaveDto> listDtos = new ArrayList<ClientSaveDto>();
		if (clients == null) {
			return null;
		} else  {
			for(Client client : clients) {
				listDtos.add(ClientDtoConverter.convertToDto(client));
			}
			return listDtos;
		}
		
	}

}
