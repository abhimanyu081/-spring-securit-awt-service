package com.abhi.springsecuritawtservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloWorldController {

	@GetMapping(name = "/")
	public String getMessage() {
		return "Hello world !!";
	}
	
}
