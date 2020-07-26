package com.jpop.bookservice.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BookEvent {

	private int bookId;
	
	private boolean bookAssigned;
}
