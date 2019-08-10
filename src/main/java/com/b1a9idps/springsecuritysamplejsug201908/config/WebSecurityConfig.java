package com.b1a9idps.springsecuritysamplejsug201908.config;

import com.b1a9idps.springsecuritysamplejsug201908.enums.Role;
import com.b1a9idps.springsecuritysamplejsug201908.security.access.RoleEvaluator;
import com.b1a9idps.springsecuritysamplejsug201908.security.core.userdetails.UserDetailsManager;
import com.b1a9idps.springsecuritysamplejsug201908.security.properties.SecurityRolesProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
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

	private final UserDetailsManager userDetailsManager;

	public WebSecurityConfig(UserDetailsManager userDetailsManager) {
		this.userDetailsManager = userDetailsManager;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public RoleHierarchy roleHierarchy(SecurityRolesProperties rolesProperties) {
		return rolesProperties.getRoleHierarchy();
	}

	@Bean
	public RoleEvaluator roleEvaluator(RoleHierarchy roleHierarchy) {
		return new RoleEvaluator(roleHierarchy);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
					.antMatchers("/users/create").hasRole(Role.MANAGER.name())
					.antMatchers("/users/delete/{id}").hasRole(Role.OWNER.name())
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
		auth.userDetailsService(userDetailsManager)
				.passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(WebSecurity web) {
		web.ignoring()
				.antMatchers("/webjars/**", "/css/**", "/font/**",  "/img/**", "/js/**", "/lib/**", "/svg/**");
	}
}
