/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 28/12/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.repository;

import org.asm.labs.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

	Optional<User> findByLogin(String login);
}
