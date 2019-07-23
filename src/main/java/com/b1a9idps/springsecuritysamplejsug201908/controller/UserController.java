package com.b1a9idps.springsecuritysamplejsug201908.controller;

import com.b1a9idps.springsecuritysamplejsug201908.dto.UserDto;
import com.b1a9idps.springsecuritysamplejsug201908.form.UserCreateForm;
import com.b1a9idps.springsecuritysamplejsug201908.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/users")
@Controller
public class UserController {

	private final UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public List<UserDto> list() {
		return userService.findAll();
	}

	@GetMapping("{id}")
	public UserDto get(@PathVariable Integer id) {
		return userService.findOne(id);
	}

	@PostMapping
	public UserDto create(UserCreateForm form) {
		return userService.create(form);
	}

	@DeleteMapping("{id}")
	public void delete(@PathVariable Integer id) {
		userService.delete(id);
	}
}
