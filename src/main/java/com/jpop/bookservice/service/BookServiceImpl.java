package com.jpop.bookservice.service;

import java.util.List;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.apache.commons.math.exception.NoDataException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.jpop.bookservice.dto.BookDto;
import com.jpop.bookservice.exception.BookNotFoundException;
import com.jpop.bookservice.exception.NoStockException;
import com.jpop.bookservice.model.Book;
import com.jpop.bookservice.repository.BookRepository;


@Service
@Transactional
public class BookServiceImpl implements BookService{

	@Autowired
	private BookRepository bookRepository;

	private static final int MINIMUM_BOOK_COUNT = 0;

	@Override
	public List<BookDto> getAllBooks() {
		List<BookDto> booksList= bookRepository.findAll().stream().filter(books -> books.getBookCount() > MINIMUM_BOOK_COUNT)
				.map(BookDto::toBookDto).collect(Collectors.toList());
		if(!CollectionUtils.isEmpty(booksList)) {
			return booksList;
		} else {
			throw new NoDataException();
		}
	}

	@Override
	public BookDto getBookById(int bookId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		if(optionalBook.isPresent()) {
			if(optionalBook.get().getBookCount() != MINIMUM_BOOK_COUNT) {
				return  BookDto.toBookDto(optionalBook.get());
			} else {
				throw new NoStockException(bookId);
			}
		}else {
			throw new BookNotFoundException(bookId);
		}
	}

	@Override
	public List<BookDto> getAllBooksByName(String bookName) {
		List<BookDto> booksList= bookRepository.findByBookNameContains(bookName).stream().filter(books -> books.getBookCount() > MINIMUM_BOOK_COUNT)
				.map(BookDto::toBookDto).collect(Collectors.toList());;
				if(!CollectionUtils.isEmpty(booksList)) {
					return booksList;
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
	public void deleteBook(int bookId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		if(optionalBook.isPresent()) {
			bookRepository.deleteById(bookId);
		}else {
			throw new BookNotFoundException(bookId);
		}
	}

	@Override
	public void updateBook(BookDto bookDto, int bookId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		if(optionalBook.isPresent()) {
			bookRepository.save(BookDto.fromBookDto(bookDto));
		} else {
			throw new BookNotFoundException(bookId);
		}
	}

	@Override
	public void assignBook(int bookId) {
		Optional<Book> optionalBook = bookRepository.findById(bookId);
		if(optionalBook.isPresent()) {
			Book book = optionalBook.get();
			if(book.getBookCount() > MINIMUM_BOOK_COUNT) {
				book.decrementBookCount();
			} else {
				throw new NoStockException(bookId);
			}
		} else {
			throw new BookNotFoundException(bookId);
		}
	}

	@Override
	public void returnBook(int id) {
		Optional<Book> optionalBook = bookRepository.findById(id);
		if(optionalBook.isPresent()) {
			Book book = optionalBook.get();
			book.incrementBookCount();
		} else {
			throw new BookNotFoundException(id);
		}
	}

	@Override
	public void manageBookCount(BookEvent bookEvent) {
		if(bookEvent.isBookAssigned()) {
			assignBook(bookEvent.getBookId());
		} else {
			returnBook(bookEvent.getBookId());
		}
	}
}