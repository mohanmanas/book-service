package com.jpop.bookservice.service;

import java.util.List;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.jpop.bookservice.model.Book;
import com.jpop.bookservice.repository.BookRepository;


@Service
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}
	
	@Override
	public Book getBookById(int id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		return optionalBook.isPresent() ? optionalBook.get(): null ;
	}
	
	@Override
	public List<Book> getAllBooksByName(String bookName) {
		return bookRepository.findByBookNameContains(bookName);
	}
	
	@Override
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}
	
	@Override
	public void deleteBook(int id) {
		 bookRepository.deleteById(id);
	}

	@Override
	public boolean updateBook(Book book, int id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		Book bookEntity = optionalBook.isPresent() ? optionalBook.get() : null;
		if(Objects.nonNull(bookEntity)) {
			Book updatedBook = Book.builder()
					.bookId(id)
					.bookName(StringUtils.isEmpty(book.getBookName())?bookEntity.getBookName():book.getBookName())
					.author(StringUtils.isEmpty(book.getAuthor())?bookEntity.getAuthor():book.getAuthor())
					.price(Objects.isNull(book.getPrice())?bookEntity.getPrice():book.getPrice())
					.publishedYear(Objects.isNull(book.getPublishedYear())?bookEntity.getPublishedYear():book.getPublishedYear())
					.build();
					bookRepository.save(updatedBook);
					return true;
		} else {
			return false;
		}
	}
}
