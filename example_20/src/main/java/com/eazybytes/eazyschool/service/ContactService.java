package com.eazybytes.eazyschool.service;

import org.springframework.stereotype.Service;

import com.eazybytes.eazyschool.model.Contact;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContactService {
	
	public boolean saveMessageDetails(Contact contact) {
		boolean isSaved = true;
		//TODO need to persist  the data into the DB table 
		
		log.info(contact.toString());
		return isSaved;
	}

}
