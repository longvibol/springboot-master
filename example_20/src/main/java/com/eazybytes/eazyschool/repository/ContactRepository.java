package com.eazybytes.eazyschool.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.eazybytes.eazyschool.model.Contact;

/*
@Repository stereotype annotation is used to add a bean of this class
type to the Spring context and indicate that given Bean is used to perform
DB related operations and
* */
@Repository
public interface ContactRepository extends CrudRepository<Contact, Integer>{

	List<Contact> findByStatus(String status);
	// because it will return many of contact that have status open or close that why we need List<Contact> to store it
}
