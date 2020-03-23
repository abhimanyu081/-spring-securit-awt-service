package com.abhi.springsecuritawtservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abhi.springsecuritawtservice.domain.AuthRequest;
import com.abhi.springsecuritawtservice.domain.AuthResponse;
import com.abhi.springsecuritawtservice.service.MyUserDetailsService;
import com.abhi.springsecuritawtservice.util.JwtUtils;

@RestController
//@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private MyUserDetailsService myUserDetailsService;
	
	@Autowired
	private JwtUtils jwtUtils;
	
	@PostMapping(value = "/auth")
	public AuthResponse login(@RequestBody AuthRequest authRequest) throws Exception {
		
		//UsernamePasswordAuthenticationToken standard method that spring security uses
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getPassword(), authRequest.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password",e);
		}
		
		final UserDetails myUser = myUserDetailsService.loadUserByUsername(authRequest.getUsername());
		
		final String jwtToken = jwtUtils.generateToken(myUser);
		
		return new AuthResponse(jwtToken);
	}
	
}
