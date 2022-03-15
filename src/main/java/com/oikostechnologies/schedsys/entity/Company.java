package com.oikostechnologies.schedsys.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
public class Company {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String compname;
	
	@OneToMany(mappedBy = "company")
	private Set<Department> departments;
	
	@OneToMany(mappedBy = "company")
	@Fetch(FetchMode.JOIN)
	private Set<User> user;
	
	private String color;
	
	@Transient
	public int usercount() {
		return user.size();
	}
	@Transient
	public int deptcount() {
		return departments.size();
	}
	@Transient
	public String masteradmin() {
		return user.stream().filter(x -> x.role().equalsIgnoreCase("MASTERADMIN")).findFirst().get().fullname();
	}
	
}
