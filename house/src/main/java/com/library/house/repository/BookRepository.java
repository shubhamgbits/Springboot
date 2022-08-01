package com.library.house.repository;

import org.springframework.data.repository.CrudRepository;

import com.library.house.entity.Book;

public interface BookRepository extends CrudRepository<Book, Long>{
	
}
