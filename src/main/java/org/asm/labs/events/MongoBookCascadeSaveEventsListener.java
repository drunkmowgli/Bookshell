package org.asm.labs.events;

import org.asm.labs.model.Book;
import org.asm.labs.repository.AuthorRepository;
import org.asm.labs.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.mapping.event.AbstractMongoEventListener;
import org.springframework.data.mongodb.core.mapping.event.BeforeConvertEvent;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class MongoBookCascadeSaveEventsListener extends AbstractMongoEventListener<Book> {

    private final AuthorRepository authorRepository;

    private final GenreRepository genreRepository;

    @Autowired
    public MongoBookCascadeSaveEventsListener(AuthorRepository authorRepository, GenreRepository genreRepository) {
        this.authorRepository = authorRepository;
        this.genreRepository = genreRepository;
    }


    @Override
    public void onBeforeConvert(BeforeConvertEvent<Book> event) {
        super.onBeforeConvert(event);
        Book book = event.getSource();
        if (book.getAuthors() != null) {
            book.getAuthors().stream().filter(e -> Objects.isNull(e.getId())).forEach(authorRepository::save);
        }
        if (book.getGenre() != null) {
            genreRepository.save(book.getGenre());
        }
    }

}
