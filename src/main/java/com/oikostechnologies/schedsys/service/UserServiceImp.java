package com.oikostechnologies.schedsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.oikostechnologies.schedsys.entity.User;
import com.oikostechnologies.schedsys.repo.UserRepo;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepo userrepo;
	
	@Override
	public long usercount() {
		// TODO Auto-generated method stub
		return userrepo.count();
	}

	public Page<User> getUsersByRole(String role){
		return userrepo.getUsersByRole(role, PageRequest.of(0, 5));
	}

	@Override
	public Page<User> findAllUsers() {
		return userrepo.findAll(PageRequest.of(0, 5));
	}

	
	
	@Override
	public Page<User> searchUser(String search) {
		
		return userrepo.findByFirstnameOrLastnameLike(search, search , PageRequest.of(0, 5));
	}

	@Override
	public Page<User> findAllUsers(int page) {
		return userrepo.findAll(PageRequest.of(page, 5));
	}

	@Override
	public Page<User> findAllUsers(int page, String search) {
		return userrepo.findAllByFirstnameContaining(search , PageRequest.of(page, 5));
	}

	
	
}
