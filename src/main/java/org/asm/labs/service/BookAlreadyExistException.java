package org.asm.labs.service;

public class BookAlreadyExistException extends Exception {
    public BookAlreadyExistException() {
        super("Duplicate key. Book already exist.");
    }
}
