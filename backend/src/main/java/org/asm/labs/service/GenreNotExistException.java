package org.asm.labs.service;

public class GenreNotExistException extends Exception {
	public GenreNotExistException() {
		super("Genre not exist.");
	}
}
