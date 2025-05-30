package com.eazybytes.eazyschool.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Slf4j
@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = {RequestMethod.GET, RequestMethod.POST})
    public String displayLoginPage(@RequestParam(value = "error", required = false) String error,
                                   @RequestParam(value = "logout", required = false) String logout,
                                   @RequestParam(value = "register", required = false) String register,
                                   Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect !!";
        } else if (logout != null) {
            errorMessage = "You have been successfully logged out !!";
        } else if (register != null) {
            errorMessage = "You registration successful. Login with registered credentials !!";
        }

        model.addAttribute("errorMessage", errorMessage);
        return "login.html";
    }
    
    // we create new logout controller     
    @GetMapping("/logout")
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
    	
    	// we want to get the user auth after login page 
    	Authentication auth = SecurityContextHolder.getContext().getAuthentication();
    	
    	// we check the auth user if it have 
    	if(auth != null) {
    		// it we find and see it have the auth still login when they click on logout we need to relese them auth 
    		new SecurityContextLogoutHandler().logout(request, response, auth);
    	}
    	
    	return "redirect:/login?logout=true";
    }
    

}





















