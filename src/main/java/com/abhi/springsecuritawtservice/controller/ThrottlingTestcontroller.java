package com.abhi.springsecuritawtservice.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.springsecuritawtservice.annotations.RateLimit;

@RestController
@RequestMapping("throttlingtest")
public class ThrottlingTestcontroller {

	@RequestMapping(value = "/test1")
	@RateLimit(count = 3)
	public String test1() {
		return "test 1";
	}
	
	@RequestMapping(value = "/test2")
	@RateLimit(count = 3)
	public String testw() {
		return "test 1";
	}
}
