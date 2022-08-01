package com.library.house.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity 
public class Book {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private String book_name; 
	private String book_author;
	private String section;
	private long num_copies;
	private String book_status;
	
}
