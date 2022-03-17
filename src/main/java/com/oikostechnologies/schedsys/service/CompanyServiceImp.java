package com.oikostechnologies.schedsys.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import com.oikostechnologies.schedsys.datatable.repo.CompanyDataTable;
import com.oikostechnologies.schedsys.entity.Company;
import com.oikostechnologies.schedsys.entity.User;
import com.oikostechnologies.schedsys.entity.UserRole;
import com.oikostechnologies.schedsys.event.CompanyEvent;
import com.oikostechnologies.schedsys.model.CompanyModel;
import com.oikostechnologies.schedsys.model.UserModel;
import com.oikostechnologies.schedsys.repo.CompanyRepo;
import com.oikostechnologies.schedsys.repo.RoleRepo;
import com.oikostechnologies.schedsys.repo.UserRepo;
import com.oikostechnologies.schedsys.repo.UserRoleRepo;
import com.oikostechnologies.schedsys.security.MyUserDetails;

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
	
	@Autowired
	private CompanyDataTable companytable;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Override
	public long companycount() {
		return companyrepo.count();
	}

	@Override
	public Page<Company> getCompanies() {
		return companyrepo.findAll(PageRequest.of(0, 5));
	}

	@Override
	public boolean addCompany(@AuthenticationPrincipal MyUserDetails detail,CompanyModel company, UserModel user, HttpServletRequest request) {
		
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
		publisher.publishEvent(new CompanyEvent(detail,master, applicationUrl(request)));
		return true;
	}

	private String applicationUrl(HttpServletRequest request) {
		return "https://" + request.getServerName() + request.getContextPath();
	}

	@Override
	public Page<Company> searchCompany(String search) {
		return companyrepo.findByCompnameContaining(search, PageRequest.of(0, 5));
	}

	@Override
	public DataTablesOutput<Company> findAll(DataTablesInput input) {
		return companytable.findAll(input);
	}

	@Override
	public DataTablesOutput<Company> findAll(DataTablesInput input, Specification<Company> spec) {
		return companytable.findAll(input, spec);
	}

}
