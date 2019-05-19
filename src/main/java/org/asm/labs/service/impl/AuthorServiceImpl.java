package org.asm.labs.service.impl;

import org.asm.labs.dao.AuthorDao;
import org.asm.labs.entity.Author;
import org.asm.labs.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void add(Author author) {
        authorDao.add(author);
    }

    @Override
    public List<Author> getAll() {
        return authorDao.getAll();
    }

    @Override
    public Author getByName(String name) {
        Author result = null;
        try {
            result = authorDao.getByName(name);
        } catch (EmptyResultDataAccessException e) {
            e.printStackTrace();
        }
        return result;
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
    public void remove(Author author) {
        authorDao.remove(author);
    }

    @Override
    public int count() {
        return authorDao.count();
    }
}
