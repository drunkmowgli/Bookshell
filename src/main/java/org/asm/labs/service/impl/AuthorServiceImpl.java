package org.asm.labs.service.impl;

import org.asm.labs.repository.AuthorRepositoryJpa;
import org.asm.labs.entity.Author;
import org.asm.labs.service.AuthorAlreadyExistException;
import org.asm.labs.service.AuthorDoesntExistException;
import org.asm.labs.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositoryJpa authorRepositoryJpa;

    @Autowired
    public AuthorServiceImpl(AuthorRepositoryJpa authorRepositoryJpa) {
        this.authorRepositoryJpa = authorRepositoryJpa;
    }

    @Override
    public void add(Author author) throws AuthorAlreadyExistException {

        try {
            authorRepositoryJpa.save(author);
        } catch (DuplicateKeyException e) {
            throw new AuthorAlreadyExistException();
        }
    }

    @Override
    public List<Author> getAll() {
        return authorRepositoryJpa.findAll();
    }

    @Override
    public Author getById(int authorId) throws AuthorDoesntExistException {
        try {
            return authorRepositoryJpa.findById(authorId);
        } catch (EmptyResultDataAccessException e) {
            throw new AuthorDoesntExistException();
        }

    }

    @Override
    public void remove(int authorId) throws AuthorDoesntExistException {
        try {
            Author author = authorRepositoryJpa.findById(authorId);
            authorRepositoryJpa.remove(author);
        } catch (EmptyResultDataAccessException e) {
            throw new AuthorDoesntExistException();
        }
    }

//    @Override
//    public int count() {
//        return authorRepositoryJpa.count();
//    }
}
