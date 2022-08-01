package com.revise.demo.topic;

import org.springframework.data.annotation.Id;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@Entity
public class Topic {
	
	@Id
	@GeneratedValue
	private int topicID;
	private String topicName;
	private String topicPerson;
	
	public Topic() {
	}
	
	
	public Topic(int topicID, String topicName, String topicPerson) {
		super();
		this.topicID = topicID;
		this.topicName = topicName;
		this.topicPerson = topicPerson;
	}
	public int getTopicID() {
		return topicID;
	}
	public void setTopicID(int topicID) {
		this.topicID = topicID;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getTopicPerson() {
		return topicPerson;
	}
	public void setTopicPerson(String topicPerson) {
		this.topicPerson = topicPerson;
	}

	
	
}
