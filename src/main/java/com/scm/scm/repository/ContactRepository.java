package com.scm.scm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.scm.domain.Contact;

public interface ContactRepository extends JpaRepository<Contact, Integer>{

}
