package com.abhi.springsecuritawtservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.springsecuritawtservice.annotations.RateLimit;

//@RestController
//@RequestMapping("/hello")
public class HelloWorldController {

	@GetMapping(name = "/")
	@RateLimit(count = 3)
	public String getMessage() {
		return "Hello world !!";
	}
	
}
