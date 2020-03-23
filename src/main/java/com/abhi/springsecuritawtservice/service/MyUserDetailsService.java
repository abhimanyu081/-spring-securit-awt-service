package com.abhi.springsecuritawtservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abhi.springsecuritawtservice.dao.MyUserDetailsDao;
import com.abhi.springsecuritawtservice.domain.MyUser;
import com.abhi.springsecuritawtservice.domain.MyUserPrincipal;

@Service
public class MyUserDetailsService implements UserDetailsService{

	@Autowired
	private MyUserDetailsDao myUserDetailsDao;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MyUser myUser= myUserDetailsDao.findByUserName(username);
		if(myUser==null) {
			throw new UsernameNotFoundException(username);
		}
		return new MyUserPrincipal(myUser);
	}

}
