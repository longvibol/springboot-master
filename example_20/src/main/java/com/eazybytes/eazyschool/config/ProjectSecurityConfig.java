package com.eazybytes.eazyschool.config;

import static org.springframework.security.config.Customizer.withDefaults;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
	
	
	/*
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
//		http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());	
		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		return http.build();
	}
	*/	
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeHttpRequests((requests) -> {
			try {
				requests
						.requestMatchers("/dashboard").authenticated()
						.requestMatchers("/","/home").permitAll()
						.requestMatchers("/holidays/**").permitAll()
						.requestMatchers("/contact").permitAll()
						.requestMatchers("/saveMsg").permitAll()
						.requestMatchers("/courses").authenticated()
						.requestMatchers("/about").permitAll()
						.requestMatchers("/assets/**").permitAll()	
						.requestMatchers("/login").permitAll()
						.and().formLogin().loginPage("/login")
						.defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
						.and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
						.and().httpBasic();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
				
		return http.build();
	}
	
	@Bean
	public InMemoryUserDetailsManager userDetailsManager() {
		UserDetails admin = User.withDefaultPasswordEncoder()
				.username("user")
				.password("12345")
				.roles("USER")
				.build();
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("admin")
				.password("12345")
				.roles("USER","ADMIN")
				.build();		
		return new InMemoryUserDetailsManager(user, admin);				
				
	}	

}


























