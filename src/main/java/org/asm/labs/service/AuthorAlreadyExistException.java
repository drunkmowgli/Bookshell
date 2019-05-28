package org.asm.labs.service;

import org.springframework.dao.DataAccessException;

public class AuthorAlreadyExistException extends DataAccessException {
    public AuthorAlreadyExistException() {
        super("Duplicate key. Author already exist.");
    }
}
