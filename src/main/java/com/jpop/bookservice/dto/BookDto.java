package com.jpop.bookservice.dto;

import java.math.BigDecimal;

import com.jpop.bookservice.model.Book;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class BookDto {

	private int bookId;
	
	private String bookName;
	
	private String author;
	
	private BigDecimal price;
	
	private int publishedYear;
	
	private int bookCount;
	
	private BigDecimal pricePerDay;
	
	public static BookDto toBookDto(Book book) {
		return BookDto.builder()
				.bookId(book.getBookId())
				.bookName(book.getBookName())
				.author(book.getAuthor())
				.price(book.getPrice())
				.publishedYear(book.getPublishedYear())
				.bookCount(book.getBookCount())
				.pricePerDay(book.getPricePerDay())
				.build();
	}
	
	public static Book fromBookDto(BookDto bookDto) {
		return Book.builder()
				.bookId(bookDto.getBookId())
				.bookName(bookDto.getBookName())
				.author(bookDto.getAuthor())
				.price(bookDto.getPrice())
				.publishedYear(bookDto.getPublishedYear())
				.bookCount(bookDto.getBookCount())
				.pricePerDay(bookDto.getPricePerDay())
				.build();
	}
}
