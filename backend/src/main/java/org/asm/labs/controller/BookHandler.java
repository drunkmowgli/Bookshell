/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 4/11/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.controller;

import lombok.extern.slf4j.Slf4j;
import org.asm.labs.domain.Book;
import org.asm.labs.repository.AuthorRepository;
import org.asm.labs.repository.BookRepository;
import org.asm.labs.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
    
    private final AuthorRepository authorRepository;
    
    private final GenreRepository genreRepository;
    
    
    @Autowired
    public BookHandler(BookRepository bookRepository, AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }
    
    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest.bodyToMono(HashMap.class)
                            .flatMap(payload -> newBook(String.valueOf(payload.get("title")),
                                (List<String>) payload.get("authors"),
                                (String) payload.get("genre"))
                            )
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
        return serverRequest.bodyToMono(HashMap.class)
                            .flatMap(payload -> bookRepository.findById(serverRequest.pathVariable("id"))
                                                              .flatMap(book -> updateBook(book,
                                                                  String.valueOf(payload.get("title")),
                                                                  (List<String>) payload.get("authors"),
                                                                  (String) payload.get("genre")
                                                                  )
                                                              )
                                                              .flatMap(response -> ServerResponse.created(UriComponentsBuilder
                                                                  .fromPath(serverRequest
                                                                      .uri()
                                                                      .toString())
                                                                  .buildAndExpand(response.getId()).toUri()).build()));
    }
    
    public Mono<ServerResponse> delete(ServerRequest serverRequest) {
        return bookRepository.deleteById(serverRequest.pathVariable("id"))
                             .flatMap(response -> ServerResponse.noContent().build());
    }
    
    private Mono<Book> newBook(String title, List<String> authorsIds, String genreId) {
        log.info("Новая книга с Название: {}, Идентификатором(ами) автора(ов): {}, Идентификатором(ами) жанра {}",
            title, authorsIds, genreId);
        
        var authors = authorRepository.findAllById(authorsIds).collectList();
        
        var genre = genreRepository.findById(genreId);
        
        return authors
            .flatMap(as -> genre
                .map(gs -> new Book(title, as, gs)))
            .flatMap(bookRepository::save);
    }
    
    private Mono<Book> updateBook(Book book, String title, List<String> authorsIds, String genreId) {
        
        var authors = authorRepository.findAllById(authorsIds).collectList();
        
        var genre = genreRepository.findById(genreId);
        
        return authors
            .flatMap(as -> genre
                .map(gs -> {
                        book.setTitle(title);
                        book.setAuthors(as);
                        book.setGenre(gs);
                        return Mono.just(book);
                    }
                ))
            .flatMap(savedBook -> savedBook.flatMap(bookRepository::save));
    }
    
    
}
