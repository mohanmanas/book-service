package com.jpop.bookservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.jpop.bookservice.model.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {
	
	public List<Book> findByBookNameContains(String name);
}
