package com.b1a9idps.springsecuritysamplejsug201908.entity;

import com.b1a9idps.springsecuritysamplejsug201908.enums.Gender;
import com.b1a9idps.springsecuritysamplejsug201908.enums.Role;
import org.springframework.data.annotation.Id;

public class User {
	@Id
	private final Integer id;

	private final String name;

	private final Integer age;

	private final Gender gender;

	private final Role role;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Integer getAge() {
		return age;
	}

	public Gender getGender() {
		return gender;
	}

	public Role getRole() {
		return role;
	}

	private User(Integer id, String name, Integer age, Gender gender, Role role) {
		this.id = id;
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.role = role;
	}

	public static User of(String name, Integer age, Gender gender, Role role) {
		return new User(null, name, age, gender, role);
	}

	public User withId(Integer id) {
		return new User(id, name, age, gender, role);
	}
}
