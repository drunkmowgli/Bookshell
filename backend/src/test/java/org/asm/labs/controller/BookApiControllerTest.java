package org.asm.labs.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.asm.labs.model.Author;
import org.asm.labs.model.Book;
import org.asm.labs.model.Genre;
import org.asm.labs.service.BookService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(BookApiController.class)
class BookApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Captor
    ArgumentCaptor<Long> longArgumentCaptor;


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
}