package com.jpop.bookservice.service;

import java.util.List;
import com.jpop.bookservice.model.Book;

public interface BookService {

	public List<Book> getAllBooks();

	public Book createBook(Book book);

	public Book getBookById(int id);

	public void deleteBook(int id);

	public boolean updateBook(Book book, int id);

	public List<Book> getAllBooksByName(String bookName);
}
