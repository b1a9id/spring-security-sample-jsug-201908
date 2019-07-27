package com.b1a9idps.springsecuritysamplejsug201908.service.impl;

import com.b1a9idps.springsecuritysamplejsug201908.dto.UserDto;
import com.b1a9idps.springsecuritysamplejsug201908.entity.User;
import com.b1a9idps.springsecuritysamplejsug201908.form.UserCreateForm;
import com.b1a9idps.springsecuritysamplejsug201908.repository.UserRepository;
import com.b1a9idps.springsecuritysamplejsug201908.service.UserService;
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
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}
}
