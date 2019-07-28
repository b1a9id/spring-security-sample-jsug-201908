package com.b1a9idps.springsecuritysamplejsug201908.service.impl;

import com.b1a9idps.springsecuritysamplejsug201908.dto.UserDto;
import com.b1a9idps.springsecuritysamplejsug201908.entity.User;
import com.b1a9idps.springsecuritysamplejsug201908.enums.Role;
import com.b1a9idps.springsecuritysamplejsug201908.exception.NotAllowedOperationException;
import com.b1a9idps.springsecuritysamplejsug201908.form.UserCreateForm;
import com.b1a9idps.springsecuritysamplejsug201908.repository.UserRepository;
import com.b1a9idps.springsecuritysamplejsug201908.service.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public List<UserDto> findAll() {
		List<UserDto> userDtoList = new ArrayList<>();
		userRepository.findAll().iterator()
				.forEachRemaining(user -> userDtoList.add(UserDto.newUserDto(user)));
		return userDtoList;
	}

	@Override
	public UserDto findOne(Integer id) {
		return userRepository.findById(id)
				.map(UserDto::newUserDto)
				.get();
	}

	@Override
	@PreAuthorize("hasAnyRole('OWNER', 'MANAGER')")
	public UserDto create(UserCreateForm form) {
		User user = new User();
		user.setName(form.getName());
		user.setUsername(form.getUsername());
		user.setPassword(passwordEncoder.encode(form.getPassword()));
		user.setAge(form.getAge());
		user.setGender(form.getGender());
		user.setRole(form.getRole());
		return UserDto.newUserDto(userRepository.save(user));
	}

	@Override
	@PreAuthorize("hasRole('OWNER')")
	public void delete(Integer id) {
		userRepository.findById(id)
				.filter(u -> u.getRole() != Role.OWNER)
				.orElseThrow(NotAllowedOperationException::new);
		userRepository.deleteById(id);
	}
}
