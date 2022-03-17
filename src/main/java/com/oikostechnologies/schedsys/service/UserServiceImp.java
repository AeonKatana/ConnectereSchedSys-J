package com.oikostechnologies.schedsys.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import com.oikostechnologies.schedsys.datatable.repo.UserDataTable;
import com.oikostechnologies.schedsys.entity.RegistrationToken;
import com.oikostechnologies.schedsys.entity.User;
import com.oikostechnologies.schedsys.repo.RegistrationTokenRepo;
import com.oikostechnologies.schedsys.repo.UserRepo;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private UserDataTable usertablerepo;
	
	@Autowired
	private RegistrationTokenRepo tokenrepo;
	
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
		
		return userrepo.findByFirstnameContainingOrLastnameContaining(search, search , PageRequest.of(0, 5));
	}

	@Override
	public Page<User> findAllUsers(int page) {
		return userrepo.findAll(PageRequest.of(page, 5));
	}

	@Override
	public Page<User> findAllUsers(int page, String search) {
		return userrepo.findAllByFirstnameContaining(search , PageRequest.of(page, 5));
	}

	@Override
	public DataTablesOutput<User> findAll(@Valid DataTablesInput input) {
		return usertablerepo.findAll(input);
	}

	@Override
	public void saveRegistrationToken(User user, String token) {
		
		RegistrationToken rtoken = new RegistrationToken();
		rtoken.setToken(token);
		rtoken.setUser(user);
		tokenrepo.save(rtoken);
		
	}

	
	
}
