package org.asm.labs.controller;

import org.asm.labs.model.Genre;
import org.asm.labs.service.GenreService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class GenreApiController {

	private final GenreService genreService;

	public GenreApiController(GenreService genreService) {
		this.genreService = genreService;
	}

	@GetMapping("/api/v1/genres")
	public List<Genre> getAllGenres() {
		return genreService.findAll();
	}

}
