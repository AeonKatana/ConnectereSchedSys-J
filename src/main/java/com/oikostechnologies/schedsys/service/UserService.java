package com.oikostechnologies.schedsys.service;

import org.springframework.data.domain.Page;

import com.oikostechnologies.schedsys.entity.User;

public interface UserService {

	public long usercount();
	public Page<User> getUsersByRole(String role);
	public Page<User> findAllUsers();
	public Page<User> searchUser(String search);
}
