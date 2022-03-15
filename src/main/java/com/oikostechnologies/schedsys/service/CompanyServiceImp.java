package com.oikostechnologies.schedsys.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.oikostechnologies.schedsys.entity.Company;
import com.oikostechnologies.schedsys.entity.User;
import com.oikostechnologies.schedsys.entity.UserRole;
import com.oikostechnologies.schedsys.model.CompanyModel;
import com.oikostechnologies.schedsys.model.UserModel;
import com.oikostechnologies.schedsys.repo.CompanyRepo;
import com.oikostechnologies.schedsys.repo.RoleRepo;
import com.oikostechnologies.schedsys.repo.UserRepo;
import com.oikostechnologies.schedsys.repo.UserRoleRepo;

@Service
public class CompanyServiceImp implements CompanyService {

	@Autowired
	private CompanyRepo companyrepo;
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private UserRoleRepo userrolerepo;
	
	@Autowired
	private RoleRepo rolerepo;
	
	@Override
	public long companycount() {
		return companyrepo.count();
	}

	@Override
	public Page<Company> getCompanies() {
		return companyrepo.findAll(PageRequest.of(0, 5));
	}

	@Override
	public boolean addCompany(CompanyModel company, UserModel user) {
		
		Company comp = Company.builder()
						.color(company.getCompanycolor())
						.compname(company.getCompanyname())
						.build();
		User master = User.builder()
					  .firstname(user.getFname())
					  .lastname(user.getLname())
					  .contactno(user.getContact())
					  .email(user.getUseremail())
					  .company(comp)
					  .build();
		
		UserRole ur = UserRole.builder()
						.role(rolerepo.findById(2L).orElse(null))
						.user(master)
						.build();
		
		companyrepo.save(comp);
		userrepo.save(master);
		
		userrolerepo.save(ur);
		
		
		return true;
	}

	@Override
	public Page<Company> searchCompany(String search) {
		return companyrepo.findByCompnameLike(search, PageRequest.of(0, 5));
	}

}
