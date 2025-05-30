package com.eazybytes.eazyschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eazybytes.eazyschool.model.Person;
import com.eazybytes.eazyschool.service.PersonService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("public")
public class PublicController {
	
	@Autowired
	PersonService personService;
	
	@GetMapping(value = "/register")
	public String displayRegisterPage(Model model) {
		model.addAttribute("person", new Person());
		return "register.html";
	}
	
	@PostMapping(value="/createUser")
	public String createUser(@Valid @ModelAttribute("person") Person person, Errors errors) {
		if(errors.hasErrors()) {
			return "register.html";
		}
		return "redirect:/login?register=true";
	}

}
