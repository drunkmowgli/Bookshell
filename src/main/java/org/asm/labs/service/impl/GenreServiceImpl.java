package org.asm.labs.service.impl;

import org.asm.labs.dao.GenreDao;
import org.asm.labs.entity.Genre;
import org.asm.labs.service.GenreAlreadyExistException;
import org.asm.labs.service.GenreDoesntExistException;
import org.asm.labs.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenreServiceImpl implements GenreService {

    private final GenreDao genreDao;

    @Autowired
    public GenreServiceImpl(GenreDao genreDao) {
        this.genreDao = genreDao;
    }

    @Override
    public void add(Genre genre) throws GenreAlreadyExistException {
        try {
            genreDao.add(genre);
        } catch (DuplicateKeyException e) {
            throw new GenreAlreadyExistException();
        }

    }

    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public Genre getByGenreName(String genreName) throws GenreDoesntExistException {
        try {
            return genreDao.getByGenreName(genreName);
        } catch (EmptyResultDataAccessException e) {
            throw new GenreDoesntExistException();
        }
    }

    @Override
    public Genre getById(int id) {
        return genreDao.getById(id);
    }

    @Override
    public int count() {
        return genreDao.count();
    }

    @Override
    public void remove(String genreName) {
        try {
            Genre genre = genreDao.getByGenreName(genreName);
            genreDao.remove(genre);
        } catch (EmptyResultDataAccessException e) {
            throw new GenreDoesntExistException();
        }
    }
}
