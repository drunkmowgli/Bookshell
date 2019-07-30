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

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class AuthorServiceImpl implements AuthorService {

    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public Author save(String authorName) {
        Author author = new Author(authorName);
        return authorRepository.save(author);
    }

    @Override
    public List<Author> findAll() {
        return authorRepository.findAll();
    }

    @Override
    public Author findById(long authorId) throws AuthorNotExistException {
        return authorRepository.findById(authorId).orElseThrow(AuthorNotExistException::new);
    }

    @Override
    public Author findByAuthorName(String authorName) throws AuthorNotExistException {
        return authorRepository.findByName(authorName).orElseThrow(AuthorNotExistException::new);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void delete(long authorId) throws AuthorNotExistException {
        Author author = authorRepository.findById(authorId).orElseThrow(AuthorNotExistException::new);
        authorRepository.delete(author);
    }

    @Override
    public long count() {
        return authorRepository.count();
    }
}
