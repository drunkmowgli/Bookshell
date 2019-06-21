package org.asm.labs.service;

public class AuthorAlreadyExistException extends Exception {
    public AuthorAlreadyExistException() {
        super("Duplicate key. Author already exist.");
    }
}
