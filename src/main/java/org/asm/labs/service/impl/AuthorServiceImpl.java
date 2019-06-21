package org.asm.labs.service.impl;

import org.asm.labs.dao.AuthorDao;
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

    private final AuthorDao authorDao;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @Override
    public void add(String authorName) throws AuthorAlreadyExistException {

        try {
            Author author = new Author(authorName);
            authorDao.add(author);
        } catch (DuplicateKeyException e) {
            throw new AuthorAlreadyExistException();
        }
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public Author getById(int authorId) throws AuthorDoesntExistException {
        try {
            return authorDao.getById(authorId);
        } catch (EmptyResultDataAccessException e) {
            throw new AuthorDoesntExistException();
        }

    }

    @Override
    public void remove(int authorId) throws AuthorDoesntExistException {
        try {
            Author author = authorDao.getById(authorId);
            authorDao.remove(author);
        } catch (EmptyResultDataAccessException e) {
            throw new AuthorDoesntExistException();
        }
    }

    @Override
    public int count() {
        return authorDao.count();
    }
}
