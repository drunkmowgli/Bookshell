package org.asm.labs.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Data
@Document(collection = "comments")
public class Comment {
    
    @Id
    private String id;
    
    private String commentDescription;
    
    @DBRef
    private Book book;
    
    public Comment(Book book, String commentDescription) {
        this.book = book;
        this.commentDescription = commentDescription;
    }
}
