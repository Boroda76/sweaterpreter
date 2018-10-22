package com.com.sweaterpreter.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.com.sweaterpreter.domain.Message;

public interface MessageRepository extends CrudRepository<Message, Integer> {
	
	List<Message> findByTextOrTagContaining(String filter, String filter2);
}
