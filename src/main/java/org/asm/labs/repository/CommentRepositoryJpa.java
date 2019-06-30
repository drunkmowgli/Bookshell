package org.asm.labs.repository;

import org.asm.labs.entity.Comment;

import java.util.List;

public interface CommentRepositoryJpa {

    /**
     * Save comment to DB.
     *
     * @param comment - description of comment
     */
    void save(Comment comment);

    /**
     * Get all comments from DB.
     *
     * @return List of Comments
     */
    List<Comment> findAll();

    /**
     * Find comment by ID.
     *
     * @param id - comment's id
     *
     * @return comment
     */
    Comment findById(int id);

    /**
     * Remove comment.
     *
     * @param comment - comment
     */
    void remove(Comment comment);

    /**
     * Count comments.
     *
     * @return comments count
     */
    long count();
}
