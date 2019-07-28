package com.b1a9idps.springsecuritysamplejsug201908.security.access;

import com.b1a9idps.springsecuritysamplejsug201908.enums.Role;
import com.b1a9idps.springsecuritysamplejsug201908.security.core.userdetails.AuthenticatedUser;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.Collection;

public class RoleEvaluator {

	private final RoleHierarchy roleHierarchy;

	public RoleEvaluator(RoleHierarchy roleHierarchy) {
		this.roleHierarchy = roleHierarchy;
	}

	public boolean hasRole(AuthenticatedUser authenticatedUser, Role role) {
		Collection<? extends GrantedAuthority> roles = AuthorityUtils.createAuthorityList("ROLE_" + role.name());
		Collection<? extends GrantedAuthority> reachableGrantedAuthorities = roleHierarchy.getReachableGrantedAuthorities(authenticatedUser.getAuthorities());
		return roles.stream().anyMatch(reachableGrantedAuthorities::contains);
	}
}
