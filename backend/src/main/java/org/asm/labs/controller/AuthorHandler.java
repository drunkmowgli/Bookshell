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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
public class AuthorHandler {
    
    private final AuthorRepository authorRepository;
    
    @Autowired
    public AuthorHandler(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    
    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest.body(BodyExtractors.toMono(Author.class))
                            .flatMap(authorRepository::save)
                            .flatMap(response -> ServerResponse.created(UriComponentsBuilder
                                .fromPath(serverRequest
                                    .uri()
                                    .toString())
                                .path("/{id}")
                                .buildAndExpand(response.getId()).toUri()).build());
    }
    
    @GetMapping("/api/v1/authors")
    public Mono<ServerResponse> readAll(ServerRequest serverRequest) {
        return ServerResponse.ok().body(authorRepository.findAll(), Author.class);
    }
}
