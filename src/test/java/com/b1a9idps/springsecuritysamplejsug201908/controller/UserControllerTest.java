package com.b1a9idps.springsecuritysamplejsug201908.controller;

import com.b1a9idps.springsecuritysamplejsug201908.entity.User;
import com.b1a9idps.springsecuritysamplejsug201908.enums.Role;
import com.b1a9idps.springsecuritysamplejsug201908.security.core.userdetails.AuthenticatedUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class UserControllerTest {

	@Nested
	@SpringBootTest
	class ListTest {
		@Autowired
		private WebApplicationContext context;

		private MockMvc mockMvc;

		@BeforeEach
		void beforeEach() {
			mockMvc = MockMvcBuilders.webAppContextSetup(context)
					.apply(springSecurity())
					.build();
		}

		@Test
		void success() throws Exception {
			User user = new User();
			user.setId(1);
			user.setName("内立良介");
			user.setUsername("ruchitate");
			user.setPassword("12345678");
			user.setRole(Role.OWNER);
			AuthenticatedUser authenticatedUser = new AuthenticatedUser(user);

			mockMvc.perform(get("/users")
					.with(user(authenticatedUser)))
					.andExpect(status().isOk())
					.andExpect(model().attributeExists("users"))
					.andExpect(view().name("user/list"))
					.andReturn();
		}
	}

	@Nested
	@SpringBootTest
	class GetTest {
		@Autowired
		private WebApplicationContext context;

		private MockMvc mockMvc;

		@BeforeEach
		void beforeEach() {
			mockMvc = MockMvcBuilders.webAppContextSetup(context)
					.apply(springSecurity())
					.build();
		}

		@Test
		void cannotViewUser() throws Exception {
			User user = new User();
			user.setId(1);
			user.setName("内立良介");
			user.setUsername("ruchitate");
			user.setPassword("12345678");
			user.setRole(Role.STAFF);
			AuthenticatedUser authenticatedUser = new AuthenticatedUser(user);

			mockMvc.perform(get("/users/{id}", 1)
					.with(user(authenticatedUser)))
					.andExpect(status().isForbidden())
					.andReturn();
		}
	}

}
