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
}
