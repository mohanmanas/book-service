package com.jpop.bookservice.service;

import java.util.List;

import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jpop.bookservice.dto.BookDto;
import com.jpop.bookservice.model.Book;
import com.jpop.bookservice.repository.BookRepository;


@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<BookDto> getAllBooks() {
		List<Book> booksList= bookRepository.findAll();
		return booksList.stream().map(BookDto::toBookDto).collect(Collectors.toList());
	}
	
	@Override
	public BookDto getBookById(int id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		return optionalBook.isPresent() ? BookDto.toBookDto(optionalBook.get()): null ;
	}
	
	@Override
	public List<BookDto> getAllBooksByName(String bookName) {
		List<Book> booksList= bookRepository.findByBookNameContains(bookName);
		return booksList.stream().map(BookDto::toBookDto).collect(Collectors.toList());
	}
	
	@Override
	public BookDto createBook(BookDto bookDto) {
		return BookDto.toBookDto(bookRepository.save(BookDto.fromBookDto(bookDto)));
	}
	
	@Override
	public void deleteBook(int id) {
		 bookRepository.deleteById(id);
	}

	@Override
	public boolean updateBook(BookDto bookDto, int id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		Book bookEntity = optionalBook.isPresent() ? optionalBook.get() : null;
		if(Objects.nonNull(bookEntity)) {
			Book updatedBook = Book.builder()
					.bookId(id)
					.bookName(StringUtils.isEmpty(bookDto.getBookName())?bookEntity.getBookName():bookDto.getBookName())
					.author(StringUtils.isEmpty(bookDto.getAuthor())?bookEntity.getAuthor():bookDto.getAuthor())
					.price(Objects.isNull(bookDto.getPrice())?bookEntity.getPrice():bookDto.getPrice())
					.publishedYear(Objects.isNull(bookDto.getPublishedYear())?bookEntity.getPublishedYear():bookDto.getPublishedYear())
					.build();
					bookRepository.save(updatedBook);
					return true;
		} else {
			return false;
		}
	}
}
