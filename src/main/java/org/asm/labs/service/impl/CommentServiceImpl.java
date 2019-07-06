package org.asm.labs.service.impl;

import org.asm.labs.model.Comment;
import org.asm.labs.repository.CommentRepository;
import org.asm.labs.service.CommentNotExistException;
import org.asm.labs.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED)
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void save(Comment comment) {
        this.commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(long commentId) throws CommentNotExistException {
        try {
            return commentRepository.findById(commentId).orElseThrow();
        } catch (NoSuchElementException e) {
            throw new CommentNotExistException();
        }
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void remove(long commentId) throws CommentNotExistException {
        try {
            Comment comment = commentRepository.findById(commentId).orElseThrow();
            commentRepository.delete(comment);
        } catch (NoSuchElementException e) {
            throw new CommentNotExistException();
        }
    }

    @Override
    public long count() {
        return commentRepository.count();
    }
}
