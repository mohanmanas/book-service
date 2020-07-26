package com.jpop.bookservice.service;

import java.util.List;
import com.jpop.bookservice.dto.BookDto;

public interface BookService {

	public List<BookDto> getAllBooks();

	public BookDto createBook(BookDto bookDto);

	public BookDto getBookById(int id);

	public void deleteBook(int id);

	public void updateBook(BookDto bookDto, int id);

	public List<BookDto> getAllBooksByName(String bookName);
	
	public void assignBook(int id);
	
	public void returnBook(int id);
	
	public void manageBookCount(BookEvent bookEvent);
}
