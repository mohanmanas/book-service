package com.epam.bookservice.controller;

import java.net.URI;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.epam.bookservice.model.Book;
import com.epam.bookservice.service.BookService;

@RestController
@RequestMapping(value="/books")
public class BookContoller {

	@Autowired
	BookService bookService;

	@GetMapping
	public ResponseEntity<List<Book>> getAllBooks(@RequestParam(required = false) String bookName) {
		List<Book> response;
		if(StringUtils.isEmpty(bookName)) {
			response =  bookService.getAllBooks();
		} else {
			response =  bookService.getAllBooksByName(bookName);
		}
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		Book response =  bookService.getBookById(id);
		if(Objects.nonNull(response)) {
			return ResponseEntity.ok().body(response);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PostMapping
	public ResponseEntity<Object> createBook(@RequestBody Book book) {
		Book response =  bookService.createBook(book);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(response.getBookId()).toUri();
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteBook(@PathVariable int id) {
		bookService.deleteBook(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Object> updateStudent(@RequestBody Book book, @PathVariable int id) {
		bookService.updateBook(book, id);
		return ResponseEntity.noContent().build();
	}
}
