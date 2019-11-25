/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 16/11/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.controller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;

@Configuration
public class RestRouteConfig {
    
    @Bean
    RouterFunction<?> routerFunction(AuthorHandler authorHandler,
                                     BookHandler bookHandler,
                                     GenreHandler genreHandler,
                                     CommentHandler commentHandler) {
        return RouterFunctions.route(RequestPredicates.POST("/api/v1/authors"), authorHandler::create)
                              .andRoute(RequestPredicates.GET("/api/v1/authors"), authorHandler::readAll)
                              .andRoute(RequestPredicates.POST("/api/v1/books"), bookHandler::create)
                              .andRoute(RequestPredicates.GET("/api/v1/books"), bookHandler::readAll)
                              .andRoute(RequestPredicates.GET("/api/v1/books/{id}"), bookHandler::readOne)
                              .andRoute(RequestPredicates.PUT("/api/v1/books/{id}"), bookHandler::update)
                              .andRoute(RequestPredicates.DELETE("/api/v1/books/{id}"), bookHandler::delete)
                              .andRoute(RequestPredicates.POST("/api/v1/genres"), genreHandler::create)
                              .andRoute(RequestPredicates.GET("/api/v1/genres"), genreHandler::readAll)
                              .andRoute(RequestPredicates.POST("/api/v1/books/{id}/comments"), commentHandler::create)
                              .andRoute(RequestPredicates.GET("/api/v1/books/{id}/comments"), commentHandler::read);
    }
}
