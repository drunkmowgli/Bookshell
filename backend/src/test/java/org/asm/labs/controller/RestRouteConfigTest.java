/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 4/11/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.controller;

import org.asm.labs.domain.Author;
import org.asm.labs.domain.Genre;
import org.asm.labs.repository.AuthorRepository;
import org.asm.labs.repository.BookRepository;
import org.asm.labs.repository.GenreRepository;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.EntityExchangeResult;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.server.RouterFunction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestRouteConfigTest {
    
    
    @MockBean
    private AuthorRepository authorRepository;
    
    @MockBean
    private GenreRepository genreRepository;
    
    @MockBean
    private BookRepository bookRepository;
    
    @Autowired
    RouterFunction routerFunction;
    
    
    @Test
    @DisplayName("Должен вернуть статус код 200 на запрос получения всех авторов")
    public void shouldReturnStatusCode200onRequestReadAllAuthors() {
        Author authorOne = new Author("1", "Author Test #1");
        Author authorTwo = new Author("2", "Author Test #2");
        
        given(authorRepository.findAll()).willReturn(Flux.just(authorOne, authorTwo));
        
        WebTestClient webTestClient = WebTestClient
            .bindToRouterFunction(routerFunction)
            .build();
        
        EntityExchangeResult<List<Author>> result = webTestClient
            .get()
            .uri("/api/v1/authors")
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .expectBodyList(Author.class)
            .returnResult();
        
        Objects.requireNonNull(result.getResponseBody()).forEach(System.out::println);
        
    }
    
    @Test
    @DisplayName("Должен корректно создать сущность автора и вернуть статус код 201")
    public void shouldReturnStatusCode201onRequestCreateAuthor() {
        Author authorNew = new Author("Author Test #1");
        Author authorSaved = new Author("1", "Author Test #1");
        
        given(authorRepository.save(authorNew)).willReturn(Mono.just(authorSaved));
        
        WebTestClient webTestClient = WebTestClient
            .bindToRouterFunction(routerFunction)
            .build();
        
        EntityExchangeResult<Author> result = webTestClient
            .post()
            .uri("/api/v1/authors")
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .body(Mono.just(authorNew), Author.class)
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(Author.class)
            .consumeWith(response -> {
                assertEquals("/api/v1/authors/1",
                    String.valueOf(response.getResponseHeaders().getLocation()));
            })
            .returnResult();
        
        System.out.println(result.getResponseHeaders().getLocation());
    }
    
    @Test
    @DisplayName("Должен корректно вернуть статус код 200 на запрос получения списка жанров")
    public void shouldReturnStatusCode200onRequestReadAllGenres() {
        Genre genreOne = new Genre("1", "Comics");
        Genre genreTwo = new Genre("2", "Novel");
        
        given(genreRepository.findAll()).willReturn(Flux.just(genreOne, genreTwo));
        
        WebTestClient webTestClient = WebTestClient
            .bindToRouterFunction(routerFunction)
            .build();
        
        EntityExchangeResult<List<Genre>> result = webTestClient
            .get()
            .uri("/api/v1/genres")
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .expectBodyList(Genre.class)
            .returnResult();
        
        Objects.requireNonNull(result.getResponseBody()).forEach(System.out::println);
    }
    
    @Test
    @DisplayName("Должен корректно создать сущность жанр и вернуть статус код 201")
    public void shouldReturnStatusCode201onRequestCreateGenre() {
        Genre genreNew = new Genre("Comics");
        Genre genreSaved = new Genre("1", "Comics");
        
        given(genreRepository.save(genreNew)).willReturn(Mono.just(genreSaved));
        
        WebTestClient webTestClient = WebTestClient
            .bindToRouterFunction(routerFunction)
            .build();
        
        EntityExchangeResult<Genre> result = webTestClient
            .post()
            .uri("/api/v1/genres")
            .accept(MediaType.APPLICATION_JSON_UTF8)
            .body(Mono.just(genreNew), Genre.class)
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(Genre.class)
            .consumeWith(response -> {
                assertEquals("/api/v1/genres/1",
                    String.valueOf(response.getResponseHeaders().getLocation()));
            })
            .returnResult();
        
        System.out.println(result.getResponseHeaders().getLocation());
    }

//    @Test
//    public void addBookTest() {
//        List<Author> authors = new ArrayList<>() {
//            {
//                add(new Author("1", "Author Test #1"));
//                add(new Author("2", "Author Test #2"));
//            }
//        };
//        Genre genre = new Genre("1", "Comics");
//        Book bookNew = new Book("Book Test #1", authors, genre);
//        Book bookSaved = new Book("1", "Book Test #1", authors, genre);
//        given(bookRepository.save(bookNew)).willReturn(Mono.just(bookSaved));
//
//        WebTestClient webTestClient = WebTestClient
//            .bindToRouterFunction(routerFunction)
//            .build();
//
//        EntityExchangeResult<Book> result = webTestClient
//            .post()
//            .uri("/api/v1/books")
//            .accept(MediaType.APPLICATION_JSON_UTF8)
//            .body(Mono.just(bookNew), Book.class)
//            .exchange()
//            .expectStatus()
//            .isCreated()
//            .expectBody(Book.class)
//            .consumeWith(response -> {
//                assertEquals("/api/v1/books/1",
//                    String.valueOf(response.getResponseHeaders().getLocation()));
//            })
//            .returnResult();
//
//        System.out.println(result.getResponseHeaders().getLocation());
//
//    }
    
}