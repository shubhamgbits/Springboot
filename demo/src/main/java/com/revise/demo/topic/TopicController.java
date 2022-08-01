package com.revise.demo.topic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopicController {
	
	@Autowired //marks the variable which needs dependency injection
	private TopicService topicService;
	
	@GetMapping("/")
	public ArrayList<?> testing(){
		return topicService.test();
	}
	
	@GetMapping("topics")
	public List<?> topics(){
		return topicService.getAllTopics();
	}
	
	@GetMapping("topic/{id}")
	public Topic getTopic(@PathVariable int id) {
		Topic found= topicService.getTopic(id);
		
		if(found!=null)
			return found;
		
		return null;
	}
	
	@PostMapping("topic/add")
	public String addTopic(@RequestBody Topic topic) {
		if(topic!=null) {
			topicService.addTopic(topic);
			return "success";
		}
		
		return null;
	}
	
	
}
