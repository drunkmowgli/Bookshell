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
    public void add(Author author) throws AuthorAlreadyExistException {

        try {
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
    public Author getByName(String name) throws AuthorDoesntExistException {
        try {
            return authorDao.getByName(name);
        } catch (EmptyResultDataAccessException e) {
            throw new AuthorDoesntExistException();
        }
    }

    @Override
    public Author getById(int id) {
        Author result = null;
        try {
            result = authorDao.getById(id);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void remove(String authorName) throws AuthorDoesntExistException {
        try {
            Author author = authorDao.getByName(authorName);
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
