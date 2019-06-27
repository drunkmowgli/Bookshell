package org.asm.labs.service.impl;

import org.asm.labs.repository.GenreDao;
import org.asm.labs.entity.Genre;
import org.asm.labs.service.GenreDoesntExistException;
import org.asm.labs.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public Genre getById(int id) throws GenreDoesntExistException {
        try {
            return genreDao.getById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new GenreDoesntExistException();
        }
    }

    @Override
    public int count() {
        return genreDao.count();
    }

}
