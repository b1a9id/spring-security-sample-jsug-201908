package com.b1a9idps.springsecuritysamplejsug201908.entity;

import com.b1a9idps.springsecuritysamplejsug201908.enums.Gender;
import com.b1a9idps.springsecuritysamplejsug201908.enums.Role;
import org.springframework.data.annotation.Id;

public class User {
	@Id
	private Integer id;

	private String name;

	private String password;

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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
