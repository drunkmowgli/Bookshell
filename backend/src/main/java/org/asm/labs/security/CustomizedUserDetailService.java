package org.asm.labs.security;

import org.asm.labs.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomizedUserDetailService implements UserDetailsService {

	private final UserRepository userRepository;

	@Autowired
	public CustomizedUserDetailService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		return userRepository.findByLogin(login).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
	}

}