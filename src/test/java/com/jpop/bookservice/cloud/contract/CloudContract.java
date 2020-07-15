package com.jpop.bookservice.cloud.contract;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.test.context.junit4.SpringRunner;

import com.jpop.bookservice.BookServiceApplication;
import com.jpop.bookservice.dto.BookDto;
import com.jpop.bookservice.repository.BookRepository;
import com.jpop.bookservice.service.BookService;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BookServiceApplication.class, webEnvironment = WebEnvironment.MOCK)
@AutoConfigureMessageVerifier
public class CloudContract {

	@MockBean
	private BookService bookService;

	@Autowired
	private BookRepository bookRepository;

	@Before
	public void setup() {

		RestAssuredMockMvc.config = RestAssuredMockMvc.config().mockMvcConfig(
				RestAssuredMockMvc.config().getMockMvcConfig().dontAutomaticallyApplySpringSecurityMockMvcConfigurer());

		RestAssuredMockMvc.standaloneSetup(bookRepository);

		Mockito.when(bookService.getAllBooks()).thenReturn(getMockData1());

	}

	private List<BookDto> getMockData1() {
		return Arrays.asList(BookDto.builder().bookId(100).bookName("Book").author("Camille").publishedYear(2015).price(new BigDecimal(1000)).build(),
				BookDto.builder().bookId(101).bookName("Book1").author("Davina").publishedYear(1996).price(new BigDecimal(500)).build());
	}

}
