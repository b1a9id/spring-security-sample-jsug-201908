package com.b1a9idps.springsecuritysamplejsug201908.service.impl;

import com.b1a9idps.springsecuritysamplejsug201908.dto.UserDto;
import com.b1a9idps.springsecuritysamplejsug201908.form.UserCreateForm;
import com.b1a9idps.springsecuritysamplejsug201908.repository.UserRepository;
import com.b1a9idps.springsecuritysamplejsug201908.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

	private final UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public List<UserDto> findAll() {
		return new ArrayList<>(userRepository.findAll());
	}

	@Override
	public UserDto findOne(Integer id) {
		return userRepository.findOne(id);
	}

	@Override
	public UserDto create(UserCreateForm form) {
		UserDto userDto = UserDto.of(
				null,
				form.getName(),
				form.getAge(),
				form.getGender(),
				form.getRole());
		return userRepository.create(userDto);
	}

	@Override
	public void delete(Integer id) {
		userRepository.delete(id);
	}
}
