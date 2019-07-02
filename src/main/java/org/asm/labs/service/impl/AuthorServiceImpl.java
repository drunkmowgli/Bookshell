package org.asm.labs.service.impl;

import org.asm.labs.entity.Author;
import org.asm.labs.repository.AuthorRepositoryJpa;
import org.asm.labs.service.AuthorNotExistException;
import org.asm.labs.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepositoryJpa authorRepositoryJpa;

    @Autowired
    public AuthorServiceImpl(AuthorRepositoryJpa authorRepositoryJpa) {
        this.authorRepositoryJpa = authorRepositoryJpa;
    }

    @Override
    public void save(Author author) {
        authorRepositoryJpa.save(author);
    }

    @Override
    public List<Author> findAll() {
        return authorRepositoryJpa.findAll();
    }

    @Override
    public Author findById(int authorId) throws AuthorNotExistException {
        try {
            return authorRepositoryJpa.findById(authorId);
        } catch (NoResultException e) {
            throw new AuthorNotExistException();
        }

    }

    @Override
    public void remove(int authorId) throws AuthorNotExistException {
        try {
            Author author = authorRepositoryJpa.findById(authorId);
            authorRepositoryJpa.remove(author);
        } catch (NoResultException e) {
            throw new AuthorNotExistException();
        }
    }

    @Override
    public long count() {
        return authorRepositoryJpa.count();
    }
}
