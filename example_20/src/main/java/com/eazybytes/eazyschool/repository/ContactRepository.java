package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Contact;

import jakarta.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;



/*
@Repository stereotype annotation is used to add a bean of this class
type to the Spring context and indicate that given Bean is used to perform
DB related operations and
* */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

	List<Contact> findByStatus(String status);

//	@Query("SELECT c FROM Contact c WHERE c.status = :status")
//	@Query(value = "SELECT * FROM contact_msg c WHERE c.status= :status",nativeQuery = true)
	@Query(value = "SELECT * FROM contact_msg c WHERE c.status= :status",nativeQuery = true)
    Page<Contact> findByStatus(@Param("status") String st, Pageable pageable);


	//update JPQL
	@Transactional
	@Modifying
	@Query("UPDATE Contact c SET c.status = ?1 WHERE c.contactId = ?2")
	int updateStatusById(String status, int id);
	
	Page<Contact> findOpenMsgs(String status, Pageable pageable);
	
	// NameQuery, We need to mention the "updateMsgStatus" in the Entity Contact in order to make Spring Mapping 	
	@Transactional
	@Modifying
	int updateMsgStatus(String status, int id);
	
	
}











