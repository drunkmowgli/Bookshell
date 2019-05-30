package org.asm.labs.service;

public class GenreAlreadyExistException extends Exception {
    public GenreAlreadyExistException() {
        super("Duplicate key. Genre already exist.");
    }
}
