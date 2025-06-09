package com.eazybytes.eazyschool.repository;

import com.eazybytes.eazyschool.model.Courses;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoursesRepository extends JpaRepository<Courses, Integer> {
	
	List<Courses> findByOrderByNameDesc();
	List<Courses> findByOrderByName(); //by default it will short by Ascending (small to big) 

}
