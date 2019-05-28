package org.asm.labs.service;

import org.springframework.dao.EmptyResultDataAccessException;

public class AuthorDoesntExistException extends EmptyResultDataAccessException {
    public AuthorDoesntExistException() {
        super("Author doesnt exist.", 0);
    }
}
