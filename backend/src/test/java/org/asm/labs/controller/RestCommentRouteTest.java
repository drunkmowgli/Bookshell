package org.asm.labs.controller;


import org.asm.labs.domain.Author;
import org.asm.labs.domain.Book;
import org.asm.labs.domain.Comment;
import org.asm.labs.domain.Genre;
import org.asm.labs.repository.BookRepository;
import org.asm.labs.repository.CommentRepository;
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
public class RestCommentRouteTest {
    
    @MockBean
    private CommentRepository commentRepository;
    
    @MockBean
    private BookRepository bookRepository;
    
    @Autowired
    RouterFunction routerFunction;
    
    @Test
    @DisplayName("Должен корректно создать комментарий и вернуть статус код 201")
    public void shouldReturnStatusCode201onRequestCreateComment() {
        List<Author> authors = new ArrayList<>() {
            {
                add(new Author("1", "Author #Test 1"));
                add(new Author("2", "Author #Test 2"));
            }
        };
        Genre genre = new Genre("1", "Comics");
        Book book = new Book("1", "Title Test #1", authors, genre);
        Comment comment = new Comment(book, "Comment Description Test #1");
        Comment savedComment = new Comment("1", "Comment Description Test #1", book);
        given(bookRepository.findById("1")).willReturn(Mono.just(book));
        
        given(commentRepository.save(comment)).willReturn(Mono.just(savedComment));
        
        WebTestClient webTestClient = WebTestClient
            .bindToRouterFunction(routerFunction)
            .build();
        
        EntityExchangeResult<Comment> result = webTestClient
            .post()
            .uri("/api/v1/books/1/comments")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .syncBody("{\"commentDescription\": \"Comment Description Test #1\"}")
            .exchange()
            .expectStatus()
            .isCreated()
            .expectBody(Comment.class)
            .consumeWith(response -> {
                assertEquals("/api/v1/books/1/comments/1",
                    String.valueOf(response.getResponseHeaders().getLocation()));
            }).returnResult();
        
        System.out.println(result.getResponseHeaders().getLocation());
        
    }
    
    @Test
    @DisplayName("Должен корректно вернуть список комментариев к книге и статус код 200")
    public void shouldReturnStatusCode200onRequestReadComments() {
        List<Author> authors = new ArrayList<>() {
            {
                add(new Author("1", "Author #Test 1"));
                add(new Author("2", "Author #Test 2"));
            }
        };
        Genre genre = new Genre("1", "Comics");
        Book book = new Book("1", "Title Test #1", authors, genre);
        List<Comment> comments = new ArrayList<>() {
            {
                add(new Comment("1", "Test Comment #1", book));
                add(new Comment("2", "Test Comment #2", book));
            }
        };
        given(bookRepository.findById("1")).willReturn(Mono.just(book));
        given(commentRepository.findByBookId("1")).willReturn(Flux.fromIterable(comments));
        
        WebTestClient webTestClient = WebTestClient
            .bindToRouterFunction(routerFunction)
            .build();
        
        EntityExchangeResult<List<Comment>> result = webTestClient
            .get()
            .uri("/api/v1/books/1/comments")
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .expectBodyList(Comment.class)
            .returnResult();
        
        Objects.requireNonNull(result.getResponseBody()).forEach(System.out::println);
        
    }
}
