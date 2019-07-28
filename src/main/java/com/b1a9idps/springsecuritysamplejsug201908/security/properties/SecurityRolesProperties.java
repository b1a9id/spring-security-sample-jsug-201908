package com.b1a9idps.springsecuritysamplejsug201908.security.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.security.access.hierarchicalroles.RoleHierarchyUtils.roleHierarchyFromMap;
import static org.springframework.util.CollectionUtils.isEmpty;

@Component
@ConfigurationProperties("security.roles")
public class SecurityRolesProperties {

	private Map<String, List<String>> hierarchyMap = new LinkedHashMap<>();

	public Map<String, List<String>> getHierarchyMap() {
		return hierarchyMap;
	}

	public void setHierarchyMap(Map<String, List<String>> hierarchyMap) {
		this.hierarchyMap = hierarchyMap;
	}

	public RoleHierarchy getRoleHierarchy() {
		RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
		String hierarchy = isEmpty(hierarchyMap) ? "" : roleHierarchyFromMap(hierarchyMap);
		roleHierarchy.setHierarchy(hierarchy);
		return roleHierarchy;
	}

}
