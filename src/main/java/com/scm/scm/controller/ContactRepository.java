package com.scm.scm.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.scm.scm.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

	@Query("from Contact as c where c.user.id =:uID")
	public Page<Contact>  findContactsByUser(@Param("uID") int uID, Pageable pageable);
}
