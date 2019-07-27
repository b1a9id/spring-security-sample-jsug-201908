package com.b1a9idps.springsecuritysamplejsug201908.form;

import com.b1a9idps.springsecuritysamplejsug201908.enums.Gender;
import com.b1a9idps.springsecuritysamplejsug201908.enums.Role;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserCreateForm {
	@NotEmpty
	private String name;
	@NotNull
	private Integer age;
	@NotNull
	private Gender gender;
	@NotNull
	private Role role;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
