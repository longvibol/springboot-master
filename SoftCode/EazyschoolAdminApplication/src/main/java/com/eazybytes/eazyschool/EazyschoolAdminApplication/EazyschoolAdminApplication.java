package com.eazybytes.eazyschool.EazyschoolAdminApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@SpringBootApplication
public class EazyschoolAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(EazyschoolAdminApplication.class, args);
	}

}
