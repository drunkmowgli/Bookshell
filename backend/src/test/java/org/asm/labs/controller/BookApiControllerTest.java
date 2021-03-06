package org.asm.labs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.asm.labs.model.Author;
import org.asm.labs.model.Book;
import org.asm.labs.model.Genre;
import org.asm.labs.repository.BookRepository;
import org.asm.labs.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.*;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookApiController.class)
class BookApiControllerTest {

	@Captor
	ArgumentCaptor<Long> longArgumentCaptor;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private BookService bookService;
	@MockBean
	private BookRepository bookRepository;

	@WithMockUser
	@DisplayName("Должен вернуть статуст код 200 на запрос получения всех книг")
	@Test
	@SneakyThrows
	void shouldReturn200onGetAllBooks() {
		Book book = new Book(
				0,
				"Book MVC #Test",
				Collections.singleton(new Author(0, "Author MVC #Test")),
				new Genre(0, "Genre MVC #Test")

		);
		when(bookService.findAll()).thenReturn(Collections.singletonList(book));
		mockMvc.perform(get("/api/v1/books"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].id", is(0)))
				.andExpect(jsonPath("$[0].title", is("Book MVC #Test")))
				.andDo(print());
		verify(bookService, times(1)).findAll();
		verifyNoMoreInteractions(bookService);
	}

	@DisplayName("Должен проверить, что метод сервиса на удаление книги вызывается")
	@Test
	@SneakyThrows
	void shouldCheckThatServiceMethodWasCalled() {
		bookService.remove(0L);
		verify(bookService).remove(longArgumentCaptor.capture());
		assertEquals(0L, longArgumentCaptor.getValue().byteValue());
	}

	@WithMockUser
	@DisplayName("Должен вернуть статус код 200 на запрос получения книги")
	@Test
	@SneakyThrows
	void shouldReturn200onGetBookById() {
		Book book = new Book(
				0,
				"Book MVC #Test",
				Collections.singleton(new Author(0, "Author MVC #Test")),
				new Genre(0, "Genre MVC #Test")

		);
		when(bookService.findById(0L)).thenReturn(book);
		ObjectMapper objectMapper = new ObjectMapper();
		String bookJsonString = objectMapper.writeValueAsString(book);
		mockMvc.perform(get("/api/v1/books/{id}", "0")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(bookJsonString)
		)
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", is(0)))
				.andExpect(jsonPath("$.title", is("Book MVC #Test")))
				.andDo(print());
	}

	@WithMockUser(roles = "ADMIN")
	@DisplayName("Должен вернуть статус код 204 на запрос удаления книги")
	@Test
	@SneakyThrows
	void shouldReturn204onDeleteBook() {
		Book book = new Book(
				0,
				"Book MVC #Test",
				Collections.singleton(new Author(0, "Author MVC #Test")),
				new Genre(0, "Genre MVC #Test")

		);
		when(bookRepository.findById(0L)).thenReturn(Optional.of(book));
		doNothing().when(bookService).remove(0L);
		mockMvc.perform(delete("/api/v1/books/{id}", "0"))
				.andExpect(status().isOk())
				.andDo(print());
	}

	@WithMockUser
	@DisplayName("Должен вернуть статус код 201 на запрос создания книги")
	@Test
	@SneakyThrows
	void shouldReturnStatusCode201OnRequestToCreateBook() {
		String bookTitle = "Book MVC #Test";
		List<Long> authorsIds = new ArrayList<>();
		authorsIds.add(1L);
		authorsIds.add(2L);
		String payloadBook = "{\"title\" : \"Book MVC #Test\", \"authors\" : [1, 2], \"genre\" : \"Genre MVC #Test\"}";
		Set<Author> authors = new HashSet<>();
		Author authorOne = new Author(1, "Author #1");
		Author authorTwo = new Author(2, "Author #2");
		authors.add(authorOne);
		authors.add(authorTwo);
		Book book = new Book(
				"Book MVC #Test",
				authors,
				new Genre(1, "Genre MVC #Test")
		);
		when(bookService.save(bookTitle, authorsIds, "Genre MVC #Test")).thenReturn(book);
		mockMvc.perform(post("/api/v1/books")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(payloadBook)
		)
				.andExpect(status().isCreated())
				.andExpect(redirectedUrl("http://localhost/api/v1/books/0"))
				.andDo(print());
	}

	@WithMockUser(roles = "ADMIN")
	@DisplayName("Должен вернуть статус код 201 на запрос об обновлении информации о книге")
	@Test
	@SneakyThrows
	void shouldReturnStatusCode201OnRequestToUpdateBook() {
		Set<Author> authors = new HashSet<>();
		Author authorOne = new Author(1, "Author #1");
		Author authorTwo = new Author(2, "Author #2");
		authors.add(authorOne);
		authors.add(authorTwo);
		Book book = new Book(
				"Book MVC #Test",
				authors,
				new Genre(1, "Genre MVC #Test")
		);
		String payloadBookUpdate = "{\"title\" : \"New Book MVC #Test\", \"authors\" : [1], \"genre\" : \"New Genre MVC #Test\"}";
		when(bookService.findById(0L)).thenReturn(book);
		mockMvc.perform(put("/api/v1/books/{id}", "0")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(payloadBookUpdate)
		)
				.andExpect(status().isCreated())
				.andExpect(redirectedUrl("http://localhost/api/v1/books/0"))
				.andDo(print());
	}
}