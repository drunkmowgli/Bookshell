package org.asm.labs.controller;

import org.asm.labs.controller.request.CommentPostRequestBody;
import org.asm.labs.model.Comment;
import org.asm.labs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class CommentApiController {

	private final CommentService commentService;

	@Autowired
	public CommentApiController(CommentService commentService) {
		this.commentService = commentService;
	}

	@GetMapping("/api/v1/books/{id}/comments")
	public List<Comment> getAllCommentToBook(@PathVariable long id) {
		return commentService.findByBookId(id);
	}

	@PostMapping("/api/v1/books/{id}/comments")
	public ResponseEntity<Comment> addComment(@RequestBody CommentPostRequestBody commentPostRequestBody,
	                                          @PathVariable long id) {
		String commentDescription = commentPostRequestBody.getCommentDescription();
		Comment comment = commentService.save(commentDescription, id);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(comment.getId())
				.toUri();
		return ResponseEntity.created(location).build();

	}
}
