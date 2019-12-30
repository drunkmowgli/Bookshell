package org.asm.labs.controller;

import org.asm.labs.controller.request.AuthorPostRequestBody;
import org.asm.labs.model.Author;
import org.asm.labs.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class AuthorApiController {

	private final AuthorService authorService;

	@Autowired
	public AuthorApiController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@GetMapping("/api/v1/authors")
	public List<Author> getAllAuthors() {
		return authorService.findAll();
	}

	@PostMapping("/api/v1/authors")
	public ResponseEntity<Author> addAuthor(@RequestBody AuthorPostRequestBody authorRequestPostRequestBody) {
		String authorName = authorRequestPostRequestBody.getName();
		Author author = authorService.save(authorName);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(author.getId())
				.toUri();
		return ResponseEntity.created(location).build();
	}

}
