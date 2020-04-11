package com.abhi.springsecuritawtservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.abhi.springsecuritawtservice.domain.TableApiAccessTime;

@Repository
public interface ApiAccessTimeRepo extends JpaRepository<TableApiAccessTime, Long> {
	
	public TableApiAccessTime findByUser(String user);

}