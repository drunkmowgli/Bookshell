package org.asm.labs.repository;

import org.asm.labs.model.Author;
import org.asm.labs.model.Genre;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

public class BookRepositoryCustomImpl implements BookRepositoryCustom {

    private final MongoTemplate mongoTemplate;

    public BookRepositoryCustomImpl(MongoTemplate mongoTemplate) {this.mongoTemplate = mongoTemplate;}


    @Override
    public void removeGenreArrayElementsById(String id) {
        Query query = Query.query(Criteria.where("$id").is(new ObjectId(id)));
        Update update = new Update().pull("genre", query);
        mongoTemplate.updateMulti(new Query(), update, Genre.class);
    }

    @Override
    public void removeAuthorArrayElementsById(String id) {
        Query query = Query.query(Criteria.where("$id").is(new ObjectId(id)));
        Update update = new Update().pull("authors", query);
        mongoTemplate.updateMulti(new Query(), update, Author.class);
    }
}
