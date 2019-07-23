package com.b1a9idps.springsecuritysamplejsug201908.service.impl;

import com.b1a9idps.springsecuritysamplejsug201908.dto.UserDto;
import com.b1a9idps.springsecuritysamplejsug201908.entity.User;
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
		User user = User.of(
				form.getName(),
				form.getAge(),
				form.getGender(),
				form.getRole());
		return UserDto.newUserDto(userRepository.save(user));
	}

	@Override
	public void delete(Integer id) {
		userRepository.deleteById(id);
	}
}
