package com.fr.adaming.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fr.adaming.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
