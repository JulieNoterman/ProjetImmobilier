package com.fr.adaming;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProjetImmobilierApplication {

	private final static Logger logger = LogManager.getLogger(ProjetImmobilierApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(ProjetImmobilierApplication.class, args);
		
		logger.warn("coucou");
		logger.error("BOOOOOOOOOOOB");
		logger.debug("BOOOOOOOOOOOB2");
	}

}
