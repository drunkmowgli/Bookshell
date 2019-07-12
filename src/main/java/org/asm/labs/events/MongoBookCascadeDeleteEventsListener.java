package org.asm.labs.events;

import org.asm.labs.model.Book;
import org.asm.labs.repository.CommentRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;

@Component
public class MongoBookCascadeDeleteEventsListener extends AbstractMongoEventListener<Book> {

    private final CommentRepository commentRepository;

    @Autowired
    public MongoBookCascadeDeleteEventsListener(CommentRepository commentRepository) {this.commentRepository = commentRepository;}


    @Override
    public void onAfterDelete(AfterDeleteEvent<Book> event) {
        super.onAfterDelete(event);
        Document source = event.getSource();
        String id = source.get("_id").toString();
        commentRepository.removeBookArrayElementsById(id);
    }
}
