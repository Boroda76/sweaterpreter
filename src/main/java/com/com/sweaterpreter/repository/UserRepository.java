package com.com.sweaterpreter.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.com.sweaterpreter.domain.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByUsername(String username);
}
