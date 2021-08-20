package com.scm.scm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.scm.scm.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
