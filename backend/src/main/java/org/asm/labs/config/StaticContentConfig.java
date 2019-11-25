/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 20/11/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class StaticContentConfig {
    
    @Bean
    public RouterFunction<ServerResponse> htmlRouter(@Value("classpath:/public/index.html") Resource html) {
        return route(
            GET("/"),
            request -> ok()
                .contentType(MediaType.TEXT_HTML)
                .syncBody(html)
        );
    }
    
    @Bean
    public RouterFunction<ServerResponse> imgRouter() {
        return RouterFunctions.resources("/img/**", new ClassPathResource("img/"));
    }
    
}
