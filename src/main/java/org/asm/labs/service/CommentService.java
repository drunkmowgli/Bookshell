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
     * Get Author from DB by id.
     *
     * @param commentId - Comment's id
     *
     * @return Author
     */
    Comment findById(String commentId) throws CommentNotExistException;

    /**
     * Remove Comment from DB.
     *
     * @param commentId - Comment's ID
     */
    void remove(String commentId) throws CommentNotExistException;

    /**
     * Count number of Comments in DB.
     *
     * @return number of Comments
     */
    long count();
}
