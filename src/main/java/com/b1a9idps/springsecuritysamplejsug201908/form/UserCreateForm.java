package com.b1a9idps.springsecuritysamplejsug201908.form;

import com.b1a9idps.springsecuritysamplejsug201908.enums.Gender;
import com.b1a9idps.springsecuritysamplejsug201908.enums.Role;

public interface UserCreateForm {
	String getName();

	Integer getAge();

	Gender getGender();

	Role getRole();
}
