package org.asm.labs.service;

import org.springframework.dao.DataAccessException;

public class BookDoesntExistException extends DataAccessException {

    public BookDoesntExistException() {
        super("Book doesn't exist.");
    }
}
