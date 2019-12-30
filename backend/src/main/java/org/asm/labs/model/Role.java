/*
 * Copyright (c) 2019.
 *
 * Created by drunkmowgli on 28/12/2019
 *
 * @author drunkmowgli
 */

package org.asm.labs.model;


import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
	ROLE_ANONYMOUS,
	ROLE_USER,
	ROLE_ADMIN;

	@Override
	public String getAuthority() {
		return name();
	}
}
