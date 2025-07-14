package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Courses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(path = "courses") // remain rest api 
//@RepositoryRestResource(exported = false) // disable export rest api to outside
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
	
	List<Courses> findByOrderByNameDesc();
	List<Courses> findByOrderByName(); //by default it will short by Ascending (small to big) 

}
