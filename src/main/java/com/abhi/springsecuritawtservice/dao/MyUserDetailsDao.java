package com.abhi.springsecuritawtservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.abhi.springsecuritawtservice.domain.MyUser;

public interface MyUserDetailsDao extends JpaRepository<MyUser, Long> {

	public MyUser findByUserName(String userName);
	
}
