package com.b1a9idps.springsecuritysamplejsug201908.security.core.userdetails;

import com.b1a9idps.springsecuritysamplejsug201908.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class UserDetailsManager implements UserDetailsService {

	private final UserRepository userRepository;

	public UserDetailsManager(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository.findByUsername(username)
				.map(AuthenticatedUser::new)
				.orElseThrow(() -> new UsernameNotFoundException("username not found"));
	}
}
