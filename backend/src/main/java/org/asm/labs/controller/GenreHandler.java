/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 15/11/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.controller;

import org.asm.labs.domain.Genre;
import org.asm.labs.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import org.springframework.web.util.UriComponentsBuilder;
import reactor.core.publisher.Mono;

@Component
public class GenreHandler {
    
    private final GenreRepository genreRepository;
    
    @Autowired
    public GenreHandler(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }
    
    @PostMapping("/api/v1/genres")
    public Mono<ServerResponse> create(ServerRequest serverRequest) {
        return serverRequest.body(BodyExtractors.toMono(Genre.class))
                            .flatMap(genreRepository::save)
                            .flatMap(response -> ServerResponse.created(UriComponentsBuilder
                                .fromPath(serverRequest
                                    .uri()
                                    .toString())
                                .path("/{id}")
                                .buildAndExpand(response.getId()).toUri()).build());
    }
    
    @GetMapping("/api/v1/genres")
    public Mono<ServerResponse> readAll(ServerRequest serverRequest) {
        return ServerResponse.ok().body(genreRepository.findAll(), Genre.class);
    }
}
