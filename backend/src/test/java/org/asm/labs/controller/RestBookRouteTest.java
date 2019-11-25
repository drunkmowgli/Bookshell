package org.asm.labs.controller;

import org.asm.labs.domain.Author;
import org.asm.labs.domain.Book;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestBookRouteTest {
    
    @MockBean
    private AuthorRepository authorRepository;
    
    @MockBean
    private GenreRepository genreRepository;
    
    @MockBean
    private BookRepository bookRepository;
    
    @Autowired
    RouterFunction routerFunction;
    
    @Test
    @DisplayName("Должен корректно создать сущность книга и вернуть статус код 201")
    public void shouldReturnStatusCode201onRequestCreateBook() {
        List<Author> authors = new ArrayList<>() {
            {
                add(new Author("1", "Author #Test 1"));
                add(new Author("2", "Author #Test 2"));
            }
        };
        List<String> authorsIds = List.of("1", "2");
        given(authorRepository.findAllById(authorsIds)).willReturn(Flux.fromIterable(authors));
        Genre genre = new Genre("1", "Comics");
        given(genreRepository.findById("1")).willReturn(Mono.just(genre));
        Book bookNew = new Book("Title Test", authors, genre);
        Book bookSaved = new Book("1", "Title Test", authors, genre);
        
        given(bookRepository.save(bookNew)).willReturn(Mono.just(bookSaved));
        
        WebTestClient webTestClient = WebTestClient
            .bindToRouterFunction(routerFunction)
            .build();
        
        EntityExchangeResult<Book> result = webTestClient
            .post()
            .uri("/api/v1/books")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .syncBody("{\"title\": \"Title Test\", \"authors\": [\"1\", \"2\"], \"genre\": \"1\"}")
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(Book.class)
            .consumeWith(response -> {
                assertEquals("/api/v1/books/1",
                    String.valueOf(response.getResponseHeaders().getLocation()));
            }).returnResult();
        
        System.out.println(result.getResponseHeaders().getLocation());
        
    }
    
    @Test
    @DisplayName("Должен корректно вернуть список книг и статус код 200")
    public void shouldReturnStatusCode200onRequestReadAllBook() {
        List<Author> authors = new ArrayList<>() {
            {
                add(new Author("1", "Author #Test 1"));
                add(new Author("2", "Author #Test 2"));
            }
        };
        Genre genre = new Genre("1", "Comics");
        List<Book> books = new ArrayList<>() {
            {
                add(new Book("1", "Test Title #1", authors, genre));
                add(new Book("2", "Test Title #2", authors, genre));
            }
        };
        
        given(bookRepository.findAll()).willReturn(Flux.fromIterable(books));
        
        WebTestClient webTestClient = WebTestClient
            .bindToRouterFunction(routerFunction)
            .build();
        
        EntityExchangeResult<List<Book>> result = webTestClient
            .get()
            .uri("/api/v1/books")
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .expectBodyList(Book.class)
            .returnResult();
        
        Objects.requireNonNull(result.getResponseBody()).forEach(System.out::println);
        
    }
    
    @Test
    @DisplayName("Должен корректно вернуть книгку и статус код 200")
    public void shouldReturnStatusCode200onRequestReadBook() {
        List<Author> authors = new ArrayList<>() {
            {
                add(new Author("1", "Author #Test 1"));
                add(new Author("2", "Author #Test 2"));
            }
        };
        Genre genre = new Genre("1", "Comics");
        Book book = new Book("1", "Title Test #1", authors, genre);
        
        given(bookRepository.findById("1")).willReturn(Mono.just(book));
        
        WebTestClient webTestClient = WebTestClient
            .bindToRouterFunction(routerFunction)
            .build();
        
        EntityExchangeResult<Book> result = webTestClient
            .get()
            .uri("/api/v1/books/1")
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .expectBody(Book.class)
            .returnResult();
        
        System.out.println(result.getResponseBody());
        
        
    }
    
    @Test
    @DisplayName("Должен корректно обновить сущность книга и вернуть статус код 201")
    public void shouldReturnStatusCode201onRequestUpdateBook() {
        List<Author> authors = new ArrayList<>() {
            {
                add(new Author("1", "Author #Test 1"));
                add(new Author("2", "Author #Test 2"));
            }
        };
        List<String> authorsIds = List.of("1", "2");
        given(authorRepository.findAllById(authorsIds)).willReturn(Flux.fromIterable(authors));
        Genre genre = new Genre("1", "Comics");
        given(genreRepository.findById("1")).willReturn(Mono.just(genre));
        
        Book book = new Book("1", "Title Test Before", authors, genre);
        Book updatedBook = new Book("1", "Title Test After", authors, genre);
        
        given(bookRepository.findById("1")).willReturn(Mono.just(book));
        
        given(bookRepository.save(updatedBook)).willReturn(Mono.just(updatedBook));
        
        
        WebTestClient webTestClient = WebTestClient
            .bindToRouterFunction(routerFunction)
            .build();
        
        EntityExchangeResult<Book> result = webTestClient
            .put()
            .uri("/api/v1/books/1")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .syncBody("{\"title\": \"Title Test After\", \"authors\": [\"1\", \"2\"], \"genre\": \"1\"}")
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(Book.class)
            .consumeWith(response -> {
                assertEquals("/api/v1/books/1",
                    String.valueOf(response.getResponseHeaders().getLocation()));
            }).returnResult();
        
        System.out.println(result.getResponseHeaders().getLocation());
        
    }
    
    @Test
    @DisplayName("Должен корректно удалить сущность книга и вернуть статус код 204")
    public void shouldReturnStatusCode204onRequestDeleteBook() {
        given(bookRepository.deleteById("1")).willReturn(Mono.empty());
        
        WebTestClient webTestClient = WebTestClient
            .bindToRouterFunction(routerFunction)
            .build();
        
        webTestClient
            .delete()
            .uri("/api/v1/books/1")
            .exchange()
            .expectStatus()
            .isOk();
    }
}
