package com.b1a9idps.springsecuritysamplejsug201908.security;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestBuilders.formLogin;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@SpringBootTest
class LoginFormTest {
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
	void loginSuccess() throws Exception {
		MvcResult result = mockMvc.perform(formLogin().user("ruchitate").password("12345678"))
				.andReturn();
		Assertions.assertThat(result.getResponse())
				.extracting(MockHttpServletResponse::getStatus, MockHttpServletResponse::getRedirectedUrl)
				.containsExactly(HttpStatus.FOUND.value(), "/users");
	}

	@Test
	void loginFailed() throws Exception {
		MvcResult result = mockMvc.perform(formLogin().user("ruchitate").password("test"))
				.andReturn();
		Assertions.assertThat(result.getResponse())
				.extracting(MockHttpServletResponse::getStatus, MockHttpServletResponse::getRedirectedUrl)
				.containsExactly(HttpStatus.FOUND.value(), "/login?error");
	}
}
