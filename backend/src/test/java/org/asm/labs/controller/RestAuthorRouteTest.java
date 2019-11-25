/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 4/11/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.controller;

import org.asm.labs.domain.Author;
import org.asm.labs.repository.AuthorRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
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
public class RestAuthorRouteTest {
    
    
    @MockBean
    private AuthorRepository authorRepository;
    
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
    
    
}