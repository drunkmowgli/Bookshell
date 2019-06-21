package org.asm.labs.service;

public class BookDoesntExistException extends Exception {

    public BookDoesntExistException() {
        super("Book doesn't exist.");
    }
}
