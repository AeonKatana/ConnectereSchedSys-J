package com.oikostechnologies.schedsys.service;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;

import com.oikostechnologies.schedsys.entity.Company;
import com.oikostechnologies.schedsys.model.CompanyModel;
import com.oikostechnologies.schedsys.model.UserModel;

public interface CompanyService {

	public long companycount();
	public Page<Company> getCompanies();
	public boolean addCompany(CompanyModel company, UserModel user);
	public Page<Company> searchCompany(String search);
	public DataTablesOutput<Company> findAll(DataTablesInput input);
	public DataTablesOutput<Company> findAll(DataTablesInput input ,Specification<Company> spec);
}
