package com.jpop.bookservice.model;


import java.math.BigDecimal;


import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Book {

	@Id
	private int bookId;
	
	private String bookName;
	
	private String author;
	
	private BigDecimal price;
	
	private int publishedYear;
}