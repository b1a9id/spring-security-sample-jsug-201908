package com.b1a9idps.springsecuritysamplejsug201908.service;

import com.b1a9idps.springsecuritysamplejsug201908.dto.UserDto;
import com.b1a9idps.springsecuritysamplejsug201908.form.UserCreateForm;

import java.util.List;

public interface UserService {
	List<UserDto> findAll();

	UserDto findOne(Integer id);

	UserDto create(UserCreateForm form);

	void delete(Integer id);
}
