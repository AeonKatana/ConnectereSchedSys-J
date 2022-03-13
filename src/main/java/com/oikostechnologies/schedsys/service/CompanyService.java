package com.oikostechnologies.schedsys.service;

import java.util.List;

import com.oikostechnologies.schedsys.entity.Company;
import com.oikostechnologies.schedsys.model.CompanyModel;
import com.oikostechnologies.schedsys.model.UserModel;

public interface CompanyService {

	public long companycount();
	public List<Company> getCompanies();
	public boolean addCompany(CompanyModel company, UserModel user);
	
}
