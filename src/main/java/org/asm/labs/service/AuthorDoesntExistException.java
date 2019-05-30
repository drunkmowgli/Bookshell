package org.asm.labs.service;

public class AuthorDoesntExistException extends Exception {
    public AuthorDoesntExistException() {
        super("Author doesnt exist.");
    }
}
