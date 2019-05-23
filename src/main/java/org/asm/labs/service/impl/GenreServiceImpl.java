package org.asm.labs.service.impl;

import org.asm.labs.dao.GenreDao;
import org.asm.labs.entity.Genre;
import org.asm.labs.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void add(Genre genre) {
        genreDao.add(genre);
    }
    
    @Override
    public List<Genre> getAll() {
        return genreDao.getAll();
    }

    @Override
    public Genre getByGenreName(String genreName) {
        return genreDao.getByGenreName(genreName);
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
    public void remove(Genre genre) {
        genreDao.remove(genre);
    }
}
