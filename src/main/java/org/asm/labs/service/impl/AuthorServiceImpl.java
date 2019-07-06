package org.asm.labs.service.impl;

import org.asm.labs.model.Author;
import org.asm.labs.repository.AuthorRepository;
import org.asm.labs.service.AuthorNotExistException;
import org.asm.labs.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void save(Author author) {
        authorRepository.save(author);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(long authorId) throws AuthorNotExistException {
        try {
            return authorRepository.findById(authorId).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new AuthorNotExistException();
        }

    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void delete(long authorId) throws AuthorNotExistException {
        try {
            Author author = authorRepository.findById(authorId).orElseThrow();
            authorRepository.delete(author);
        } catch (NoSuchElementException e) {
            throw new AuthorNotExistException();
        }
    }

    @Override
    public long count() {
        return authorRepository.count();
    }
}
