package com.b1a9idps.springsecuritysamplejsug201908.dto;

import com.b1a9idps.springsecuritysamplejsug201908.entity.User;
import com.b1a9idps.springsecuritysamplejsug201908.enums.Gender;
import com.b1a9idps.springsecuritysamplejsug201908.enums.Role;

public class UserDto {
	private Integer id;

	private String name;

	private Integer age;

	private Gender gender;

	private Role role;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	private UserDto(Integer id, String name, Integer age, Gender gender, Role role) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.role = role;
	}

	public static UserDto newUserDto(User user) {
		return new UserDto(user.getId(), user.getName(), user.getAge(), user.getGender(), user.getRole());
	}
}
