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
    public List<Comment> findByBookId(long id) {
        return commentRepository.findByBookId(id);
    }
    
    @Override
    public Comment findById(long commentId) throws CommentNotExistException {
        return commentRepository.findById(commentId).orElseThrow(CommentNotExistException::new);
    }
    
    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void remove(long commentId) throws CommentNotExistException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotExistException::new);
        commentRepository.delete(comment);
    }
    
    @Override
    public long count() {
        return commentRepository.count();
    }
}