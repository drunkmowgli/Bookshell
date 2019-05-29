package org.asm.labs.service;

import java.util.NoSuchElementException;

public class BookDoesntExistException extends NoSuchElementException {
    
    public BookDoesntExistException() {
        super("Book doesn't exist.");
    }
}
