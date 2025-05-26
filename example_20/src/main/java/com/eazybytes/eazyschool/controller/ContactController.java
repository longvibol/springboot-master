package com.eazybytes.eazyschool.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.service.ContactService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ContactController {

	private final ContactService contactService;
	
	@Autowired
    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

	@RequestMapping("/contact")
	public String displayContactPage(Model model) {
		model.addAttribute("contact", new Contact());
		return "contact.html";
	}
	
	@PostMapping("/saveMsg")
	public String saveMessage(@Valid @ModelAttribute("contact") Contact contact, Errors errors) {
		if(errors.hasErrors()) {
			log.error("Contact form validation failed due to : "+ errors.toString());
			return "contact.html";
		}
		contactService.saveMessageDetails(contact);
		return "redirect:/contact";
		
	}	
	
	 @RequestMapping("/displayMessages")
	    public ModelAndView displayMessages(Model model) {
	        List<Contact> contactMsgs = contactService.findMsgsWithOpenStatus();
	        ModelAndView modelAndView = new ModelAndView("messages.html");
	        modelAndView.addObject("contactMsgs",contactMsgs);
	        return modelAndView;
	    }

 
	    @GetMapping("/closeMsg")
	    public String closeMsg(@RequestParam int id, Authentication authentication) {
	        contactService.updateMsgStatus(id,authentication.getName());
	        return "redirect:/displayMessages";
	    }
	
	
	
	

	/*
	 * @RequestMapping("/contact") public String displayContactPage() { return
	 * "contact.html"; }
	 */

	/*
	 * // @RequestMapping(value="/saveMsg", method = RequestMethod.POST)
	 * 
	 * @PostMapping("/saveMsg") public ModelAndView saveMessage(@RequestParam String
	 * name, @RequestParam String mobileNum,
	 * 
	 * @RequestParam String email,@RequestParam String subject, @RequestParam String
	 * message) {
	 * 
	 * log.info("Name : " + name); log.info("Mobile Number : " + mobileNum);
	 * log.info("Email Address : " + email); log.info("Subject : " + subject);
	 * log.info("Message : " + message);
	 * 
	 * return new ModelAndView("redirect:/contact"); }
	 * 
	 */

	/*
	@PostMapping("/saveMsg")
	public ModelAndView saveMessage(Contact contact) {
		contactService.saveMessageDetails(contact);
		return new ModelAndView("redirect:/contact");
	}
	
	*/
	
	
	
	
	
	
	
	
	
	

}
