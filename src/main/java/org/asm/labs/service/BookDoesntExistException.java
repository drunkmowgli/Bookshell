package org.asm.labs.service;

import org.springframework.dao.EmptyResultDataAccessException;

public class BookDoesntExistException extends EmptyResultDataAccessException {

    public BookDoesntExistException() {
        super("Book doesn't exist.", 0);
    }
}
