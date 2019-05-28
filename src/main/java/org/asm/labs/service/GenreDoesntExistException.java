package org.asm.labs.service;

import org.springframework.dao.DataAccessException;

public class GenreDoesntExistException extends DataAccessException {
    public GenreDoesntExistException() {
        super("Genre doesnt exist.");
    }
}
