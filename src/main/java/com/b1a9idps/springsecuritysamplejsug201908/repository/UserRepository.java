package com.b1a9idps.springsecuritysamplejsug201908.repository;

import com.b1a9idps.springsecuritysamplejsug201908.dto.UserDto;
import com.b1a9idps.springsecuritysamplejsug201908.enums.Gender;
import com.b1a9idps.springsecuritysamplejsug201908.enums.Role;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

@Repository
public class UserRepository {

	private static Map<Integer, UserDto> userDtoMap;

	static {
		userDtoMap = Map.of(
				1, UserDto.of(1, "内立　良介", 29, Gender.MAN, Role.OWNER),
				2, UserDto.of(2, "新垣　結衣", 31, Gender.WOMAN, Role.MANAGER),
				3, UserDto.of(3, "山崎　賢人", 24, Gender.MAN, Role.STAFF));
	}

	public Collection<UserDto> findAll() {
		return userDtoMap.values();
	}

	public UserDto findOne(Integer id) {
		return userDtoMap.get(id);
	}

	public UserDto create(UserDto userDto) {
		int size = userDtoMap.size();
		userDto.setId(size + 1);
		return userDtoMap.put(userDto.getId(), userDto);
	}

	public void delete(Integer id) {
		userDtoMap.remove(id);
	}
}
