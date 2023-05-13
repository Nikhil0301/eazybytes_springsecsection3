package com.eazybytes.config;

import javax.sql.DataSource;

import org.apache.catalina.realm.UserDatabaseRealm;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
	//We define our own Custom requirements to secure apis
	//Here we define that which apis are need to be secured as our bussiness requirements
	
	/**
     *  Below is the custom security configurations
     */
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests()
		    .requestMatchers("/myAccount" , "/myBalance" , "/myLoans","/myCards")
		    .authenticated()
		    .requestMatchers("/contact" , "/notices")
		    .permitAll()
		    .and().formLogin()
		    .and().httpBasic();
		return http.build();
	}
	
	/* Aproach 1 where we use withDefaultPasswordEncoder() method
	 * while creating the user details 
	@Bean
	InMemoryUserDetailsManager userDetailService() {
		/*UserDetails admin  = User.withDefaultPasswordEncoder()
				.username("admin")
				.password("12345")
				.authorities("admin")
				.build();
		
		UserDetails user  = User.withDefaultPasswordEncoder()
				.username("user")
				.password("123456")
				.authorities("read")
				.build();
		
		UserDetails admin  = User.withUsername("admin")
				.password("12345")
				.authorities("admin")
				.build();
		
		UserDetails user  = User.withUsername("user")
				.password("123456")
				.authorities("read")
				.build();
		return new InMemoryUserDetailsManager(admin , user);
	}*/
	//create A bean of type PasswordEncoder
	//Not recommended for production use
	
	@Bean
	public UserDetailsService userDetailsService(DataSource dataSource) {
		return new JdbcUserDetailsManager(dataSource);
	}
	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}
	
	/* Aproach 2 where we use withDefaultPasswordEncoder() method
	 * while creating the user details */
}
