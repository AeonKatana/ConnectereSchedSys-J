package com.oikostechnologies.schedsys.service;

import org.springframework.data.domain.Page;

import com.oikostechnologies.schedsys.entity.User;

public interface UserService {

	public long usercount();
	public Page<User> getUsersByRole(String role);
	public Page<User> findAllUsers();
	public Page<User> findAllUsers(int page);
	public Page<User> findAllUsers(int page , String search);
	public Page<User> searchUser(String search);
}
