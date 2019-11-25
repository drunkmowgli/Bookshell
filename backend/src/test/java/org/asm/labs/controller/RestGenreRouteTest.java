package org.asm.labs.controller;

import org.asm.labs.domain.Genre;
import org.asm.labs.repository.GenreRepository;
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
public class RestGenreRouteTest {
    
    
    @MockBean
    private GenreRepository genreRepository;
    
    
    @Autowired
    RouterFunction routerFunction;
    
    
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
    
    
}
