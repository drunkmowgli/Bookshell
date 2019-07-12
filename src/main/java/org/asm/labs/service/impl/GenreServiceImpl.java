package org.asm.labs.service.impl;

import org.asm.labs.model.Genre;
import org.asm.labs.repository.GenreRepository;
import org.asm.labs.service.GenreNotExistException;
import org.asm.labs.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public void save(String genreName) {
        Genre genre = new Genre(genreName);
        genreRepository.save(genre);

    }

    @Override
    public List<Genre> findAll() {
        return genreRepository.findAll();
    }

    @Override
    public Genre findById(String genreId) throws GenreNotExistException {
        return genreRepository.findById(genreId).orElseThrow(GenreNotExistException::new);
    }

    @Override
    public void remove(String genreId) throws GenreNotExistException {
        Genre genre = genreRepository.findById(genreId).orElseThrow(GenreNotExistException::new);
        genreRepository.delete(genre);
    }

    @Override
    public long count() {
        return genreRepository.count();
    }

}
