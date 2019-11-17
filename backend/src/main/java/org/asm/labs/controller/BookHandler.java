/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 4/11/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.controller;

import lombok.extern.slf4j.Slf4j;
import org.asm.labs.domain.Author;
import org.asm.labs.domain.Book;
import org.asm.labs.domain.Genre;
import org.asm.labs.repository.BookRepository;
import org.asm.labs.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Component
public class BookHandler {
    
    private final BookRepository bookRepository;
    
    private final GenreRepository genreRepository;
    
    
    @Autowired
    public BookHandler(BookRepository bookRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
    }
    
    // TODO: Требуется разобраться, как получать жанр из реактивного репозитория жанров по идентификатору
    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest.body(BodyExtractors.toMono(HashMap.class))
                            .flatMap(payload -> {
                                Book book = new Book(
                                    String.valueOf(payload.get("title")),
                                    (List<Author>) payload.get("authors"),
                                    (Genre) payload.get("genre")
                                );
                                return Mono.just(book);
                            })
                            .flatMap(bookRepository::save)
                            .flatMap(response -> ServerResponse.created(UriComponentsBuilder
                                .fromPath(serverRequest
                                    .uri()
                                    .toString())
                                .path("/{id}")
                                .buildAndExpand(response.getId()).toUri()).build());
    }
    
    public Mono<ServerResponse> readAll(ServerRequest serverRequest) {
        return ServerResponse.ok().body(bookRepository.findAll(), Book.class);
    }
    
    public Mono<ServerResponse> readOne(ServerRequest serverRequest) {
        return ServerResponse.ok().body(bookRepository.findById(serverRequest.pathVariable("id")), Book.class);
    }
    
    public Mono<ServerResponse> update(ServerRequest serverRequest) {
        return bookRepository.findById(serverRequest.pathVariable("id"))
                             .flatMap(book -> serverRequest.body(BodyExtractors.toMono(Book.class))
                                                           .flatMap(bookRequestBody -> {
                                                               book.setTitle(bookRequestBody.getTitle());
                                                               book.setAuthors(bookRequestBody.getAuthors());
                                                               book.setGenre(bookRequestBody.getGenre());
                                                               return bookRepository.save(book);
                                                           }))
                             .flatMap(response -> ServerResponse.created(UriComponentsBuilder
                                 .fromPath(serverRequest
                                     .uri()
                                     .toString())
                                 .path("/{id}")
                                 .buildAndExpand(response.getId()).toUri()).build());
    }
    
    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        return bookRepository.deleteById(serverRequest.pathVariable("id"))
                             .flatMap(response -> ServerResponse.noContent().build());
    }
    
    
}
