package com.fr.adaming.web.dto.converter;

import java.util.ArrayList;
import java.util.List;

import com.fr.adaming.entity.Client;
import com.fr.adaming.web.dto.ClientSaveDto;

public class ClientDtoConverter {
	
	public static Client convert(ClientSaveDto dto) {
		Client client = new Client();
		client.setEmail(dto.getEmail());
		client.setFullname(dto.getFullname());
		client.setTelephone(dto.getTelephone());
		client.setType(dto.getType());
		return client;
	}
	
	public static ClientSaveDto convert(Client client) {
		ClientSaveDto dto = new ClientSaveDto();
		dto.setEmail(client.getEmail());
		dto.setFullname(client.getFullname());
		dto.setTelephone(client.getTelephone());
		dto.setType(client.getType());
		return dto;
	}
	
	
	
	public static List<Client> convert2(List<ClientSaveDto> dtos){
		List<Client> listClients = new ArrayList<Client>();
		for(ClientSaveDto dto : dtos) {
			listClients.add(ClientDtoConverter.convert(dto));
		}
		return listClients;
		
	}
	
	public static List<ClientSaveDto> convert(List<Client> clients){
		List<ClientSaveDto> listDtos = new ArrayList<ClientSaveDto>();
		for(Client client : clients) {
			listDtos.add(ClientDtoConverter.convert(client));
		}
		return listDtos;
	}

}
