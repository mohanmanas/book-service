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

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value="/books")
@Slf4j
public class BookController {

	@Autowired
	BookService bookService;

	@GetMapping
	public List<BookDto> getAllBooks() {
		log.info("Fetch books from Book API"); 
		return bookService.getAllBooks();
	}

	@GetMapping("/{id}")
	public BookDto getBook(@PathVariable("id") int bookId) {
		log.info("Fetch {} book from Book API", bookId); 
		return bookService.getBookById(bookId);
	}

	@GetMapping("/search/{bookName}")
	public List<BookDto> getBookByName(@PathVariable("bookName") String bookName) {
		log.info("Fetch {} book from Book API", bookName); 
		return bookService.getAllBooksByName(bookName);
	}

	@PostMapping
	public ResponseEntity<BookDto> createBook(@RequestBody BookDto bookDto) {
		log.info("Create book from Book API"); 
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
	public ResponseEntity<BookDto> deleteBook(@PathVariable int bookId) {
		log.info("Delete {} book from Book API", bookId); 
		bookService.deleteBook(bookId);
		return ResponseEntity.noContent().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<BookDto> updateBook(@RequestBody BookDto bookDto, @PathVariable int bookId) {
		log.info("Update {} book from Book API", bookId); 
		bookService.updateBook(bookDto, bookId);
		return ResponseEntity.noContent().build();	
	}
}
