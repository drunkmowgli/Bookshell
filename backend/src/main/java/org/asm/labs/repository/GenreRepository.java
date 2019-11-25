/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 15/11/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.repository;

import org.asm.labs.domain.Genre;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface GenreRepository extends ReactiveMongoRepository<Genre, String> {

}
