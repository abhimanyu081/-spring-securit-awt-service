package com.abhi.springsecuritawtservice.service;

public interface RateLimitingService {

	public boolean limit(String username,int apiAccessLimit);
	
}
