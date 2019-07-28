package com.b1a9idps.springsecuritysamplejsug201908.security.core.userdetails;

import com.b1a9idps.springsecuritysamplejsug201908.entity.User;
import com.b1a9idps.springsecuritysamplejsug201908.enums.Role;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class AuthenticatedUser implements UserDetails {

	private String name;

	private String username;

	private String password;

	private Role role;

	public AuthenticatedUser(User user) {
		this.name = user.getName();
		this.username = user.getUsername();
		this.password = user.getPassword();
		this.role = user.getRole();
	}

	public String getName() {
		return name;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return AuthorityUtils.createAuthorityList("ROLE_" + role.name());
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
}
