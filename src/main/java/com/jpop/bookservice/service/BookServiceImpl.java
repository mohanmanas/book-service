package com.jpop.bookservice.service;

import java.util.List;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.math.exception.NoDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jpop.bookservice.dto.BookDto;
import com.jpop.bookservice.exception.BookNotFoundException;
import com.jpop.bookservice.model.Book;
import com.jpop.bookservice.repository.BookRepository;
import com.jpop.userservice.exception.UserNotFoundException;


@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<BookDto> getAllBooks() {
		List<Book> booksList= bookRepository.findAll();
		if(CollectionUtils.isNotEmpty(booksList)) {
			return booksList.stream().map(BookDto::toBookDto).collect(Collectors.toList());
		} else {
			throw new NoDataException();
		}
	}
	
	@Override
	public BookDto getBookById(int id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if(optionalBook.isPresent()) {
			return optionalBook.isPresent() ? BookDto.toBookDto(optionalBook.get()): null ;
		}else {
			throw new BookNotFoundException(id);
		}
	}
	
	@Override
	public List<BookDto> getAllBooksByName(String bookName) {
		List<Book> booksList= bookRepository.findByBookNameContains(bookName);
		if(CollectionUtils.isNotEmpty(booksList)) {
			return booksList.stream().map(BookDto::toBookDto).collect(Collectors.toList());
		} else {
			throw new NoDataException();
		}
	}
	
	@Override
	public BookDto createBook(BookDto bookDto) {
		Optional<Book> optionalBook = bookRepository.findById(bookDto.getBookId());
		if(!optionalBook.isPresent()) {
			return BookDto.toBookDto(bookRepository.save(BookDto.fromBookDto(bookDto)));
		} else {
			throw new RuntimeException();
		}
	}
	
	@Override
	public void deleteBook(int id) {
		 bookRepository.deleteById(id);
	}

	@Override
	public void updateBook(BookDto bookDto, int id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if(optionalBook.isPresent()) {
			bookRepository.save(BookDto.fromBookDto(bookDto));
		} else {
			throw new BookNotFoundException(id);
		}
	}
}
