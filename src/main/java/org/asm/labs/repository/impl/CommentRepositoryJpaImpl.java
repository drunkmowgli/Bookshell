package org.asm.labs.repository.impl;

import org.asm.labs.entity.Comment;
import org.asm.labs.repository.CommentRepositoryJpa;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.validation.constraints.NotNull;
import java.util.List;

@Repository
public class CommentRepositoryJpaImpl implements CommentRepositoryJpa {

    @PersistenceContext
    private EntityManager em;


    @Override
    @Transactional
    public void save(@NotNull Comment comment) {
        if (comment.getId() <= 0) {
            em.persist(comment);
        } else {
            em.merge(comment);
        }
    }

    @Override
    public List<Comment> findAll() {
        TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c", Comment.class);
        return query.getResultList();
    }

    @Override
    public Comment findById(int id) throws NoResultException {
        TypedQuery<Comment> query = em.createQuery("SELECT c FROM Comment c WHERE id = :id", Comment.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void remove(@NotNull Comment comment) {
        em.remove(comment);
    }

    @Override
    public long count() {
        TypedQuery<Long> query = em.createQuery("SELECT COUNT(c) FROM Comment c", Long.class);
        return query.getSingleResult();
    }
}
