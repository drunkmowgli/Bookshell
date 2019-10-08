package org.asm.labs.service;

public class AuthorNotExistException extends Exception {
    public AuthorNotExistException() {
        super("Author not exist");
    }
}
