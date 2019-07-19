package org.asm.labs.service.impl;

import org.asm.labs.model.Book;
import org.asm.labs.model.Comment;
import org.asm.labs.repository.BookRepository;
import org.asm.labs.repository.CommentRepository;
import org.asm.labs.service.BookNotExistException;
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

    private final BookRepository bookRepository;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository, BookRepository bookRepository) {
        this.commentRepository = commentRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public Comment save(String commentDescription, String bookId) throws BookNotExistException {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotExistException::new);
        Comment comment = new Comment(commentDescription, book);
        commentRepository.save(comment);
        return comment;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(String commentId) throws CommentNotExistException {
        return commentRepository.findById(commentId).orElseThrow(CommentNotExistException::new);
    }

    @Override
    @Transactional(readOnly = false, isolation = Isolation.READ_COMMITTED)
    public void remove(String commentId) throws CommentNotExistException {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotExistException::new);
        commentRepository.delete(comment);
    }

    @Override
    public long count() {
        return commentRepository.count();
    }
}
