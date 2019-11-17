/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 4/11/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Setter
@Getter
@Document(collection = "books")
public class Book {
    
    @Id
    private String id;
    
    private String title;
    
    @DBRef
    private List<Author> authors;
    
    @DBRef
    private Genre genre;

//    private List<Comment> comments = new ArrayList<>();
    
    public Book(String title, List<Author> authors) {
        this.title = title;
        this.authors = authors;
    }
    
    public Book(String title, List<Author> authors, Genre genre) {
        this.title = title;
        this.authors = authors;
        this.genre = genre;
    }
    
}
