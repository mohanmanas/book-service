package com.jpop.bookservice.controller;

import java.net.URI;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.jpop.bookservice.dto.BookDto;
import com.jpop.bookservice.service.BookService;

@RestController
@RequestMapping(value="/books")
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping
	public List<BookDto> getAllBooks() {
		return bookService.getAllBooks();
	}
	
	@GetMapping("/{id}")
	public BookDto getBook(@PathVariable("id") int id) {
		return bookService.getBookById(id);
	}
	
	@GetMapping("/search/{bookName}")
	public List<BookDto> getBookByName(@PathVariable("bookName") String bookName) {
		return bookService.getAllBooksByName(bookName);
	}
	
	@PostMapping
	public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
		try {
			BookDto response =  bookService.createBook(bookDto);
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
					.buildAndExpand(response.getBookId()).toUri();
			return ResponseEntity.created(location).build();
		} catch(Exception exception) {
			return new ResponseEntity<>(null, HttpStatus.CONFLICT);
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<BookDto> deleteBook(@PathVariable int id) {
		bookService.deleteBook(id);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable int id) {
		bookService.updateBook(bookDto, id);
		return ResponseEntity.noContent().build();	
	}
}
