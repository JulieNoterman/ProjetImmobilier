package com.fr.adaming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.fr.adaming.web.controller.impl.BienControllerImpl;

@SpringBootApplication
public class ProjetImmobilierApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjetImmobilierApplication.class, args);

	}

}
