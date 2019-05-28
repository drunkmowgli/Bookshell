package org.asm.labs.service;

import org.springframework.dao.DataAccessException;

public class GenreAlreadyExistException extends DataAccessException {
    public GenreAlreadyExistException() {
        super("Duplicate key. Genre already exist.");
    }
}
