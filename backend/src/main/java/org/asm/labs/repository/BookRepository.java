/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 4/11/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.repository;

import org.asm.labs.domain.Book;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BookRepository extends ReactiveMongoRepository<Book, String> {
}
