package com.revise.demo.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service //Tells Spring this is a business service
public class TopicService {
	
	@Autowired
	TopicRepository topicRepository;
	
	Topic t1= new Topic(1, "UPSC", "Shubham");
	Topic t2= new Topic(2, "NGO", "Aeshvarya");
	Topic t3= new Topic(3, "Sports Analytics", "Anjul");
	Topic t4= new Topic(4, "Portfolio Management", "Nihal");
	
	List<Topic> topics = new ArrayList<>(Arrays.asList(t1, t2, t3, t4));
	
	public ArrayList<?> test(){
		ArrayList<String> h= new ArrayList<>();
		h.add("Hello Shubham");
		h.add("Welcome to your website");
		h.add("Kill it!");
		
		return h;
	}
	
	public List<?> getAllTopicsOld(){		
		return topics;
	}
	
	public List<Topic> getAllTopics(){
		
		List<Topic> topicFetch = new ArrayList<Topic>();
		
		topicRepository.findAll()
		.forEach(topics::add);
		
		return topicFetch;
	}
	
	public Topic getTopic(int id){
		Topic temp = topics.stream().filter(i->i.getTopicID()==id).findFirst().get();
		
		if(temp!=null)
			return temp;
		
		return null;
	}
	
	public void addTopicOld(Topic toAdd) {
		topics.add(toAdd);
	}
	
	public void addTopic(Topic toAdd) {
		topics.add(toAdd);
	}
}
