package com.jpop.bookservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class BookEventListener {

	@Autowired
	BookService bookService;

	@KafkaListener(topics = "book", id = "bookEventListener")
	public void consumer(String consumedMessage) {
		log.info("Book event listened");
		ObjectMapper mapper = new ObjectMapper();
		BookEvent bookEvent = null;
		try {
			bookEvent = mapper.readValue(consumedMessage, BookEvent.class);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		bookService.manageBookCount(bookEvent);
	}
}
