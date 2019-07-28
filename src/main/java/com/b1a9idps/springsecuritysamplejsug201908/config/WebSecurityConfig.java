package com.b1a9idps.springsecuritysamplejsug201908.config;

import com.b1a9idps.springsecuritysamplejsug201908.repository.UserRepository;
import com.b1a9idps.springsecuritysamplejsug201908.security.core.userdetails.UserDetailsManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final UserRepository userRepository;

	public WebSecurityConfig(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
					.antMatchers("/login").permitAll()
					.anyRequest().authenticated()
				.and()
					.formLogin()
					.loginPage("/login")
					.defaultSuccessUrl("/users", true)
				.and()
					.logout()
					.logoutSuccessUrl("/login").permitAll()
				.and()
					.csrf().disable();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(new UserDetailsManager(userRepository))
				.passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
				.antMatchers("/webjars/**", "/css/**", "/font/**",  "/img/**", "/js/**", "/lib/**", "/svg/**");
	}
}
