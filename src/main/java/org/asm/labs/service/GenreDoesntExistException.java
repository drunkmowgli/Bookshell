package org.asm.labs.service;

public class GenreDoesntExistException extends Exception {
    public GenreDoesntExistException() {
        super("Genre doesn't exist.");
    }
}
