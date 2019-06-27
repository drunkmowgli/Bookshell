//package org.asm.labs.service.impl;
//
//import org.asm.labs.repository.GenreRepositoryJpa;
//import org.asm.labs.entity.Genre;
//import org.asm.labs.service.GenreDoesntExistException;
//import org.asm.labs.service.GenreService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.EmptyResultDataAccessException;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//public class GenreServiceImpl implements GenreService {
//
//    private final GenreRepositoryJpa genreRepositoryJpa;
//
//    @Autowired
//    public GenreServiceImpl(GenreRepositoryJpa genreRepositoryJpa) {
//        this.genreRepositoryJpa = genreRepositoryJpa;
//    }
//
//    @Override
//    public List<Genre> findAll() {
//        return genreRepositoryJpa.findAll();
//    }
//
//    @Override
//    public Genre findById(int id) throws GenreDoesntExistException {
//        try {
//            return genreRepositoryJpa.findById(id);
//        } catch (EmptyResultDataAccessException e) {
//            throw new GenreDoesntExistException();
//        }
//    }
//
//    @Override
//    public int count() {
//        return genreRepositoryJpa.count();
//    }
//
//}
