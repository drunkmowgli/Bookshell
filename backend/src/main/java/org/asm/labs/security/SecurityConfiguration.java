/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 28/12/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.security;

import org.asm.labs.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	private final CustomizedUserDetailService customizedUserDetailService;

	@Autowired
	public SecurityConfiguration(@Lazy CustomizedUserDetailService customizedUserDetailService) {
		this.customizedUserDetailService = customizedUserDetailService;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customizedUserDetailService);
		auth.authenticationProvider(new AuthenticationProvider() {
			@Override
			public Authentication authenticate(Authentication authentication) throws AuthenticationException {
				String login = (String) authentication.getPrincipal();
				User authenticatedUser = customizedUserDetailService.findAndAuthenticate(login);
				if (authenticatedUser == null) {
					throw new BadCredentialsException("Username/Password does not match for " + login);
				}
				return new UsernamePasswordAuthenticationToken(authenticatedUser, null, authenticatedUser.getAuthorities());
			}

			@Override
			public boolean supports(Class<?> aClass) {
				return true;
			}
		});
	}

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

}

