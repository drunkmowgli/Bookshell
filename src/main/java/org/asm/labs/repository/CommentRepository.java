package org.asm.labs.repository;

import org.asm.labs.model.Comment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    
    @Override
    @EntityGraph("commentGraph")
    List<Comment> findAll();
}
