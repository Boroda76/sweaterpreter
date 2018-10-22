package com.com.sweaterpreter.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.com.sweaterpreter.domain.Message;
import com.com.sweaterpreter.domain.User;
import com.com.sweaterpreter.repository.MessageRepository;

@Controller
public class MainController {
	@Autowired
    private EntityManager entityManager;
	@Autowired
	private MessageRepository messageRepository;
	
	@Value("${upload.path}")
	private String uploadPath;
	
	
	@GetMapping("/")
	public String greeting() {
		return "greeting";
	}
	@GetMapping("/main")
	public String main(@RequestParam(required=false, defaultValue="") String filter,Map<String, Object> model) {
		Iterable<Message> messages=messageRepository.findAll();
		if(filter!=null && !filter.isEmpty()) {
			Query q=entityManager.createNativeQuery("SELECT id, text, tag, user_id FROM message WHERE tag like '%"+filter+"%' OR text like '%"+filter+"%'", Message.class);
		messages=q.getResultList();
		} else {
			messages=messageRepository.findAll();
		}
		model.put("filter",  filter);
		model.put("messages", messages);
		
		return "main";
	}
	@PostMapping("/main")
	public String add(
			@AuthenticationPrincipal User user,
			@RequestParam String text,
			@RequestParam String tag,
			Map<String, Object> model,
			@RequestParam("file") MultipartFile file
			) throws IllegalStateException, IOException {
		
		Message message=new Message(text, tag, user);
		if(file!=null) {
			File uploadDir=new File(uploadPath);
			if(!uploadDir.exists() && !file.getOriginalFilename().isEmpty()) {
				uploadDir.mkdir();
			}
			
			String uuidFile=UUID.randomUUID().toString();
			String resultFileName=uuidFile+"."+file.getOriginalFilename();
			file.transferTo(new File(uploadPath+"/"+resultFileName));
			message.setFilename(resultFileName);
		}
		messageRepository.save(message);

		return "redirect:/main";
	}

}
