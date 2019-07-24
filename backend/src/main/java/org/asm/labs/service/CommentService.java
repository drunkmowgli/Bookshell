package org.asm.labs.service;

import org.asm.labs.model.Comment;

import java.util.List;

public interface CommentService {
    /**
     * Add Comment to DB.
     *
     * @param comment - Comment's name
     */
    void save(Comment comment);

    /**
     * Get all Comment from DB.
     *
     * @return List of Comments
     */
    List<Comment> findAll();
    
    /**
     * Get all Comment for Book from DB.
     *
     * @param id - book's id
     *
     * @return List of books's comments
     */
    List<Comment> findByBookId(long id);

    /**
     * Get Author from DB by id.
     *
     * @param commentId - Comment's id
     *
     * @return Author
     */
    Comment findById(long commentId) throws CommentNotExistException;

    /**
     * Remove Comment from DB.
     *
     * @param commentId - Comment's ID
     */
    void remove(long commentId) throws CommentNotExistException;

    /**
     * Count number of Comments in DB.
     *
     * @return number of Comments
     */
    long count();
}
