package com.sri.sampleboot;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import com.sri.sampleboot.persistence.model.Book;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.preemptive;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.apache.commons.lang3.RandomStringUtils.randomNumeric;
import org.springframework.http.MediaType;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { SamplebootApplication.class }, webEnvironment = WebEnvironment.DEFINED_PORT)
public class BookControllerTests {

	@Before
	public void setUp() {
		RestAssured.authentication = preemptive().basic("john", "123");
	}

	private static final String API_ROOT = "http://localhost:8081/api/books";

	@Test
	public void whenGetAllBooks_thenOk() {
		final Response response = RestAssured.get(API_ROOT);
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
	}

	@Test
	public void whenGetBooksByTitle_thenOk() {
		final Book book = createRandomBook();
		createBookAsUri(book);

		final Response response = RestAssured.get(API_ROOT + "/titile/" + book.getTitle());
		assertEquals(HttpStatus.OK.value(), response.getStatusCode());
		assertTrue(response.as(List.class).size() > 0);
	}

	private Book createRandomBook() {
		final Book book = new Book();
		book.setTitle(randomAlphabetic(10));
		book.setAuthor(randomAlphabetic(15));
		return book;
	}

	private String createBookAsUri(Book book) {
		final Response response = RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(book)
				.post(API_ROOT);
		return API_ROOT + "/" + response.jsonPath().get("id");
	}

}
