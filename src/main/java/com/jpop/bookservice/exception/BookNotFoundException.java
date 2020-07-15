package com.jpop.bookservice.exception;

public class BookNotFoundException extends RuntimeException{

	public BookNotFoundException(int id) {
        super(String.format("Book with Id %d not found", id));
    }
}
