package com.abhi.springsecuritawtservice.domain;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="table_api_access_time")
public class TableApiAccessTime {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="id")
	private long id;
	
	@Column(name="user")
	private String user;
	
	@Column(name="access_count")
	private int count;
	
	@Column(name="last_access_time")
	private LocalDateTime lastAccessTime;

	
	
	public TableApiAccessTime() {
		super();
	}

	public TableApiAccessTime(String user, int count, LocalDateTime lastAccessTime) {
		super();
		this.user = user;
		this.count = count;
		this.lastAccessTime = lastAccessTime;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}


	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public LocalDateTime getLastAccessTime() {
		return lastAccessTime;
	}

	public void setLastAccessTime(LocalDateTime lastAccessTime) {
		this.lastAccessTime = lastAccessTime;
	}

	
	
}
