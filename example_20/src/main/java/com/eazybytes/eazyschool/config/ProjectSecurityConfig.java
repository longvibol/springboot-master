package com.eazybytes.eazyschool.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	        		.ignoringRequestMatchers("/saveMsg","/public/**")
	        		) 
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers("/dashboard").authenticated()
	            .requestMatchers("/displayMessages").hasRole("ADMIN")
	            .requestMatchers("/closeMsg/**").hasRole("ADMIN")
	            .requestMatchers("/", "/home", "/holidays/**", "/contact", "/saveMsg", 
	                             "/courses", "/about", "/assets/**", "/login", "/logout","/public/**")
	  
	            .permitAll()
	        )
	        .formLogin(login -> login
	            .loginPage("/login")
	            .defaultSuccessUrl("/dashboard")
	            .failureUrl("/login?error=true")
	            .permitAll()
	        )
	        
	        .httpBasic(Customizer.withDefaults());
	    

	    return http.build();
	}	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	//Create temporary user 
	@Bean
	public InMemoryUserDetailsManager userDetailsManager(PasswordEncoder passwordEncoder) {
	    UserDetails admin = User.builder()
	            .username("admin")
	            .password(passwordEncoder.encode("12345"))
	            .roles("ADMIN")
	            .build();

	    UserDetails user = User.builder()
	            .username("user")
	            .password(passwordEncoder.encode("12345"))
	            .roles("USER")
	            .build();

	    return new InMemoryUserDetailsManager(admin, user);
	}

}


























