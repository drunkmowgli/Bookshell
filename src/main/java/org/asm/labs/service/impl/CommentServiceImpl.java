package org.asm.labs.service.impl;

import org.asm.labs.entity.Comment;
import org.asm.labs.repository.CommentRepositoryJpa;
import org.asm.labs.service.CommentNotExistException;
import org.asm.labs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.NoResultException;
import java.util.List;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    private final CommentRepositoryJpa commentRepositoryJpa;

    @Autowired
    public CommentServiceImpl(CommentRepositoryJpa commentRepositoryJpa) {
        this.commentRepositoryJpa = commentRepositoryJpa;
    }

    @Override
    public void save(Comment comment) {
        this.commentRepositoryJpa.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepositoryJpa.findAll();
    }

    @Override
    public Comment findById(int commentId) throws CommentNotExistException {
        try {
            return commentRepositoryJpa.findById(commentId);
        } catch (NoResultException e) {
            throw new CommentNotExistException();
        }
    }

    @Override
    public void remove(int commentId) throws CommentNotExistException {
        try {
            Comment comment = commentRepositoryJpa.findById(commentId);
            commentRepositoryJpa.remove(comment);
        } catch (NoResultException e) {
            throw new CommentNotExistException();
        }
    }

    @Override
    public long count() {
        return commentRepositoryJpa.count();
    }
}
