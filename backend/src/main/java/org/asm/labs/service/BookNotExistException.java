package org.asm.labs.service;

public class BookNotExistException extends Exception {

	public BookNotExistException() {
		super("Book not exist");
	}
}
