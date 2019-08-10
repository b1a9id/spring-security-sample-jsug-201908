package com.b1a9idps.springsecuritysamplejsug201908.service.impl;

import com.b1a9idps.springsecuritysamplejsug201908.dto.UserDto;
import com.b1a9idps.springsecuritysamplejsug201908.exception.NotFoundException;
import com.b1a9idps.springsecuritysamplejsug201908.service.UserService;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.test.context.support.WithUserDetails;

import java.util.List;

import static com.b1a9idps.springsecuritysamplejsug201908.enums.Gender.MAN;
import static com.b1a9idps.springsecuritysamplejsug201908.enums.Gender.WOMAN;
import static com.b1a9idps.springsecuritysamplejsug201908.enums.Role.MANAGER;
import static com.b1a9idps.springsecuritysamplejsug201908.enums.Role.STAFF;

@SpringBootTest
class UserServiceImplTest {

	@Autowired
	private UserService userService;

	@Test
	@WithUserDetails(value = "yaragaki", userDetailsServiceBeanName = "userDetailsManager")
	void findAllForManager() {
		List<UserDto> result = userService.findAll();
		Assertions.assertThat(result)
				.extracting(
						UserDto::getId,
						UserDto::getName,
						UserDto::getAge,
						UserDto::getGender,
						UserDto::getRole)
				.containsExactly(
						Tuple.tuple(2, "新垣　結衣", 31, WOMAN, MANAGER),
						Tuple.tuple(3, "山崎　賢人", 24, MAN, STAFF));
	}

	@Test
	@WithUserDetails(value = "ruchitate", userDetailsServiceBeanName = "userDetailsManager")
	void deleteForOwner() {
		userService.delete(2);
		Assertions.assertThatThrownBy(() -> userService.findOne(2))
				.isInstanceOf(NotFoundException.class);
	}

	@Test
	@WithUserDetails(value = "yaragaki", userDetailsServiceBeanName = "userDetailsManager")
	void deleteForManager() {
		Assertions.assertThatThrownBy(() -> userService.delete(3))
				.isInstanceOf(AccessDeniedException.class);
	}

}
