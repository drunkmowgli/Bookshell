/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 15/11/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Document(collection = "genres")
public class Genre {
    
    @Id
    private String id;
    
    private String name;
    
    public Genre(String name) {
        this.name = name;
    }
}
