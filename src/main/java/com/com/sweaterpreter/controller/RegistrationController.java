package com.com.sweaterpreter.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.com.sweaterpreter.domain.Role;
import com.com.sweaterpreter.domain.User;
import com.com.sweaterpreter.repository.UserRepository;

@Controller
public class RegistrationController {
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/registration")
	public String registration() {
		return "registration";
	}
	@PostMapping("/registration")
	public String adduser(User user, Map<String, Object> model) {
		User dbUser=userRepository.findByUsername(user.getUsername());
		if(dbUser!=null) {
			model.put("message", "Sorry, user "+dbUser.getUsername()+" is already exists.");
			return "registration";
		} else {
			user.setActive(true);
			user.setRoles(Collections.singleton(Role.USER));
			userRepository.save(user);
		}
		return "redirect:/login";
	}
}
