package org.asm.labs.service;

import org.springframework.dao.DataAccessException;

public class BookAlreadyExistException extends DataAccessException {
    public BookAlreadyExistException() {
        super("Duplicate key. Book already exist.");
    }
}
