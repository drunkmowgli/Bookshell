/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 3/11/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "authors")
public class Author {
    
    @Id
    private String id;
    
    private String name;
    
    public Author(String name) {
        this.name = name;
    }
    
}
