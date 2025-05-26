package com.eazybytes.eazyschool.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
	    http
	    	// Ignore CSRF for this end point
	        .csrf(csrf -> 
	        		csrf
	        		.ignoringRequestMatchers("/saveMsg")
	        		.ignoringRequestMatchers(PathRequest.toH2Console())) 
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/dashboard").authenticated()
	            .requestMatchers(PathRequest.toH2Console()).permitAll()
	            .requestMatchers("/displayMessages").hasRole("ADMIN")
	            .requestMatchers("/closeMsg/**").hasRole("ADMIN")
	            .requestMatchers("/", "/home", "/holidays/**", "/contact", "/saveMsg", 
	                             "/courses", "/about", "/assets/**", "/login", "/logout","/h2-console/**")
	  
	            .permitAll()
	        )
	        .formLogin(login -> login
	            .loginPage("/login")
	            .defaultSuccessUrl("/dashboard")
	            .failureUrl("/login?error=true")
	            .permitAll()
	        )
	        
	        .httpBasic(Customizer.withDefaults())
	    .headers().frameOptions().disable();

	    return http.build();
	}	
	
	//Create temporary user 
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
				.roles("ADMIN")
				.build();		
		return new InMemoryUserDetailsManager(user, admin);				
				
	}	
	
	/*
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
//		http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());	
		http.formLogin(withDefaults());
		http.httpBasic(withDefaults());
		return http.build();
	}
	
	
	@Bean
	SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
		http.csrf().ignoringRequestMatchers("/saveMsg").and()
			.authorizeHttpRequests((requests) -> {
			try {
				requests
						.requestMatchers("/dashboard").authenticated()
						.requestMatchers("/","/home").permitAll()
						.requestMatchers("/holidays/**").permitAll()
						.requestMatchers("/contact").permitAll()
						.requestMatchers("/saveMsg").permitAll()
						.requestMatchers("/courses").permitAll()
						.requestMatchers("/about").permitAll()
						.requestMatchers("/assets/**").permitAll()	
						.requestMatchers("/login").permitAll()
						.requestMatchers("/logout").permitAll()
						.and().formLogin().loginPage("/login")
						.defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll()
//						.and().logout().logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll()
						.and().httpBasic();
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
				
		return http.build();
	}
	*/		
	
	

}


























