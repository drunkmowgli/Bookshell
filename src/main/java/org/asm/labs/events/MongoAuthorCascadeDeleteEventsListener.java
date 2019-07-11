package org.asm.labs.events;

import org.asm.labs.model.Author;
import org.asm.labs.repository.BookRepository;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.AfterDeleteEvent;
import org.springframework.stereotype.Component;

@Component
public class MongoAuthorCascadeDeleteEventsListener extends AbstractMongoEventListener<Author> {

    private final BookRepository bookRepository;

    @Autowired
    public MongoAuthorCascadeDeleteEventsListener(BookRepository bookRepository) {this.bookRepository = bookRepository;}

    @Override
    public void onAfterDelete(AfterDeleteEvent<Author> event) {
        super.onAfterDelete(event);
        Document source = event.getSource();
        String id = source.get("_id").toString();
        bookRepository.removeAuthorArrayElementsById(id);
    }
}
