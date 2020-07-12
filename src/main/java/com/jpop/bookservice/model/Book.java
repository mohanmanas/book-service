package com.jpop.bookservice.model;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Version;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

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
	
	@CreatedBy
	private String createdUser;
	
	@CreatedDate
	private LocalDateTime createdDateTime;
	
	@LastModifiedBy
	private String modifiedUser;
	
	@LastModifiedDate
	private LocalDateTime modifiedDateTime;
	
	@Version
	private long version;
}