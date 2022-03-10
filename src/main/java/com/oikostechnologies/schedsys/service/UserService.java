package com.oikostechnologies.schedsys.service;

import java.util.List;

import com.oikostechnologies.schedsys.entity.User;

public interface UserService {

	public long usercount();
	public List<User> getUsersByRole(String role);
	public List<User> findAllUsers();
	
}
