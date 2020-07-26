package com.jpop.bookservice.exception;

public class NoStockException extends RuntimeException{

	public NoStockException(int id) {
		super(String.format("Book with Id %d is out of stock", id));
	}
}
