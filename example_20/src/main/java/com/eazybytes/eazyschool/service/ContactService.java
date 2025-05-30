package com.eazybytes.eazyschool.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eazybytes.eazyschool.constants.EazySchoolConstants;
import com.eazybytes.eazyschool.model.Contact;
import com.eazybytes.eazyschool.repository.ContactRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ContactService {

	@Autowired
	private ContactRepository contactRepository;

	public ContactService() {
		System.out.println("Contact Service Bean initialized");
	}

	// Save Method

	public boolean saveMessageDetails(Contact contact) {
		boolean isSaved = false;
		contact.setStatus(EazySchoolConstants.OPEN);
		Contact saveContact = contactRepository.save(contact);
		if (null != saveContact && saveContact.getContactId() > 0) {
			isSaved = true;
		}
		return isSaved;
	}

	
	//find Method
	public List<Contact> findMsgsWithOpenStatus() {
		List<Contact> contactMsgs = contactRepository.findByStatus(EazySchoolConstants.OPEN);
		return contactMsgs;
	}

	// update Method 
	public boolean updateMsgStatus(int contactId) {
		boolean isUpdated = false;
		Optional<Contact> contact = contactRepository.findById(contactId);
		// after we success find the contact By Id we want to update the status
		contact.ifPresent(contact1 -> {
			contact1.setStatus(EazySchoolConstants.CLOSE);

		});
		Contact updateContact = contactRepository.save(contact.get()); // contact.get() because it is optional so we
																		// want to get only if the contact have

		if (updateContact != null && updateContact.getUpdatedBy() != null) {
			isUpdated = true;
		}
		return isUpdated;
	}

}
