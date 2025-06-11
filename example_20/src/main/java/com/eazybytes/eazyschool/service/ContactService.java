package com.eazybytes.eazyschool.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
    public Page<Contact> findMsgsWithOpenStatus(int pageNum,String sortField, String sortDir){
        int pageSize = 5;
        Pageable pageable = PageRequest.of(pageNum - 1, pageSize,
                sortDir.equals("asc") ? Sort.by(sortField).ascending()
                        : Sort.by(sortField).descending());
        Page<Contact> msgPage = contactRepository.findOpenMsgs(
                EazySchoolConstants.OPEN,pageable);
        return msgPage;
    }
	

	// update Method 
	public boolean updateMsgStatus(int contactId) {
		boolean isUpdated = false;		
		int rows = contactRepository.updateMsgStatus(EazySchoolConstants.CLOSE, contactId);
		if(rows > 0) {
			isUpdated = true;
		}
		
		return isUpdated;
	}

}
