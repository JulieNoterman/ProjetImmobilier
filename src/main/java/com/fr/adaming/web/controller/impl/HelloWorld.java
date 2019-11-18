package com.fr.adaming.web.controller.impl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/hello")
public class HelloWorld {
	

	@GetMapping
	public String SayHello() {
		return "Coucou";
	}
}
