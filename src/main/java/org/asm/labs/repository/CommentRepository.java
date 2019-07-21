package org.asm.labs.repository;

import org.asm.labs.model.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends MongoRepository<Comment, String>, CommentRepositoryCustom {
    
    List<Comment> findByBookId(String bookId);
}
