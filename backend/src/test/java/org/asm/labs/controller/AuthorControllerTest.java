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
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@AutoConfigureWebTestClient
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AuthorControllerTest {
    
    @Autowired
    private WebTestClient webTestClient;
    
    @MockBean
    private AuthorRepository authorRepository;
    
    private Author authorFirst = new Author("Test Author #1");
    private Author authorSecond = new Author("Test Author #2");
    
    @Test
    @DisplayName("Должен вернуть статус код 200 на запрос чтения всех авторов")
    public void shouldReturnStatusCode200onRequestReadAllAuthors() {
        when(authorRepository.findAll()).thenReturn(Flux.just(authorFirst, authorSecond));
        webTestClient
            .get()
            .uri("/api/v1/authors")
            .exchange()
            .expectStatus()
            .isOk()
            .expectBodyList(Author.class)
            .hasSize(2);
    }
    
    @Test
    @DisplayName("Должен вернуть статус код 200 на запрос создания автора")
    public void shouldReturnStatusCode200onRequestCreateAuthor() {
        when(authorRepository.save(authorFirst)).thenReturn(Mono.just(authorFirst));
        webTestClient
            .post()
            .uri("/api/v1/authors")
            .contentType(MediaType.APPLICATION_JSON_UTF8)
            .body(BodyInserters.fromObject(authorFirst))
            .exchange()
            .expectStatus()
            .isOk();
    }
    
}