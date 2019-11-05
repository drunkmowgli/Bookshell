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
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "books")
public class Book {
    
    @Id
    private String id;
    
    private String title;
    
    private List<Author> authors = new ArrayList<>();
    
//    private List<Genre> genres = new ArrayList<>();
//    private List<Comment> comments = new ArrayList<>();
    
    public Book(String title, List<Author> authors) {
        this.title = title;
        this.authors = authors;
    }
    
    
}
