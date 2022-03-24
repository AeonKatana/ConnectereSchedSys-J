package com.oikostechnologies.schedsys.entity;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String firstname;
	private String lastname;
	private String email;
	private char[] password;
	private boolean enabled;
	private long contactno;
	
	
	@OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	@JsonManagedReference
	private Set<ActivityLog> actlog;
	
	
	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY , cascade = CascadeType.ALL , orphanRemoval = true)
	@Fetch(FetchMode.JOIN)
	@JsonManagedReference
	private Set<UserRole> userrole;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JsonIgnoreProperties("user")
	private Company company;

	
	@OneToMany(mappedBy = "user" , fetch = FetchType.LAZY , cascade = CascadeType.ALL , orphanRemoval = true)
	@JsonManagedReference
	private Set<UserDepartment> userdepartment;
	
	
	@OneToMany(mappedBy = "user" , fetch =  FetchType.LAZY , cascade = CascadeType.ALL , orphanRemoval = true)
	@JsonManagedReference
	private Set<UserTask> tasks;
	
	
	@OneToMany(mappedBy = "user" , fetch=FetchType.LAZY , cascade = CascadeType.ALL , orphanRemoval = true)
	@JsonManagedReference
	private Set<DailyTask> dailies;
	
	@OneToOne(mappedBy = "user" , fetch = FetchType.LAZY , cascade = CascadeType.ALL , orphanRemoval = true)
	@JsonIgnore
	private RegistrationToken token;
	
	@OneToOne(mappedBy ="user" , fetch = FetchType.LAZY , cascade = CascadeType.ALL , orphanRemoval = true)
	@JsonIgnore
	private PasswordToken passtoken;
	
	@Transient
	public String role() {
		return userrole.stream().findFirst().get().getRole().getRolename();
	}
	
	@Transient
	public String companyname() { 
		try {
			return company.getCompname();
		}catch(Exception e) {
			return "Does not belong to a company";
		}
	}
	
	@Transient
	public String fullname() {
		return firstname + " " + lastname;
	}
	
	@Transient
	public int companysize() {
		
		try {
			return company.usercount();
		}catch(Exception e) {
			return 0;
		}
		
	}
	@Transient
	public String companycolor() {
		try {
			return company.getColor();
		}catch(Exception e) {
			return "black";
		}
	}
	
}
