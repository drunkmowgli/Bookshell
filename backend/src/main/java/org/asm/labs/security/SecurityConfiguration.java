/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 28/12/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(WebSecurity web) {
		web.ignoring().antMatchers("/js/**", "/css/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
				.csrf().disable()
				.authorizeRequests()
				.antMatchers(HttpMethod.GET, "/", "/api/v1/books").authenticated()
				.antMatchers(HttpMethod.DELETE, "/api/v1/books/**").hasAuthority("ROLE_ADMIN")
				.antMatchers(HttpMethod.PUT, "/api/v1/books/**").hasAuthority("ROLE_ADMIN")
				.antMatchers(HttpMethod.POST, "/api/v1/authors").authenticated()
				.antMatchers(HttpMethod.POST, "/api/v1/books/*/comments").authenticated()
				.and()
				.formLogin()
				.and()
				.logout();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

}

