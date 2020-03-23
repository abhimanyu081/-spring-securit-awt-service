package com.abhi.springsecuritawtservice.domain;

/**
 * to return response of an api
 * 
 * @author user
 *
 */
public class AuthResponse {

	private final String jwt;

	public AuthResponse(String jwt) {
		super();
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

}
