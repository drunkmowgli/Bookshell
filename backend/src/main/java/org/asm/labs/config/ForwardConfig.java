/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 21/11/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.GET;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
public class ForwardConfig {
    @Bean
    public RouterFunction<ServerResponse> forward(@Value("classpath:/public/index.html") Resource html) {
        return route(
            GET("/books/**"),
            request -> ok()
                .contentType(MediaType.TEXT_HTML)
                .syncBody(html)
        );
    }
}
