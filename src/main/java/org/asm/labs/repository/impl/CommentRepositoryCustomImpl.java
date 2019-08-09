package org.asm.labs.repository.impl;

import org.asm.labs.model.Book;
import org.asm.labs.repository.CommentRepositoryCustom;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class CommentRepositoryCustomImpl implements CommentRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public CommentRepositoryCustomImpl(MongoTemplate mongoTemplate) {this.mongoTemplate = mongoTemplate;}

    @Override
    public void removeBookArrayElementsById(String id) {
        Query query = Query.query(Criteria.where("$id").is(new ObjectId()));
        Update update = new Update().pull("book", query);
        mongoTemplate.updateMulti(new Query(), update, Book.class);
    }
}
