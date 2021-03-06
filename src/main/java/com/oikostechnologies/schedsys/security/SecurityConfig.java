package com.oikostechnologies.schedsys.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder(10);
	}
	
	@Autowired
	private UserDetailsService service;
	
	

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setPasswordEncoder(passwordEncoder());
		provider.setUserDetailsService(service);
		auth.authenticationProvider(provider);
	}




	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.headers().frameOptions().sameOrigin()
			.and()
			.authorizeRequests()
			.antMatchers("/resources/**","/forgot-pass/**", "/resetPass").permitAll()
			.antMatchers("/","/dashboard/**","/task/mytask/**", "/task/assignedtask/**", 
					     "/task/savemytask","/personnel/**","/companies/**", "/personnel/mycompanypeople"
					     ,"/personnel/savecard", "/task/searchtask", "/profile/**","/settings/**").authenticated()
			.and()
			.formLogin().loginPage("/login-page").usernameParameter("email").passwordParameter("pass")
			.loginProcessingUrl("/perform-login")
			.failureHandler(new AuthenticationFailureHandler() {
				
				@Override
				public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
						AuthenticationException exception) throws IOException, ServletException {
					System.out.println(exception);
					
				}
			}).failureUrl("/login-page?error")
			.and()
			.logout().logoutUrl("/perform-logout")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.logoutSuccessUrl("/login-page");
		
	}
	
	
	
	
	
}
