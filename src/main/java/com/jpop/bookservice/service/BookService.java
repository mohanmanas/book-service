package com.jpop.bookservice.service;

import java.util.List;

import com.jpop.bookservice.dto.BookDto;
import com.jpop.bookservice.model.Book;

public interface BookService {

	public List<BookDto> getAllBooks();

	public BookDto createBook(BookDto bookDto);

	public BookDto getBookById(int id);

	public void deleteBook(int id);

	public boolean updateBook(BookDto bookDto, int id);

	public List<BookDto> getAllBooksByName(String bookName);
}
