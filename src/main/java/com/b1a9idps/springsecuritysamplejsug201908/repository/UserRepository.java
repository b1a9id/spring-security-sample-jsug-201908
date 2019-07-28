package com.b1a9idps.springsecuritysamplejsug201908.repository;

import com.b1a9idps.springsecuritysamplejsug201908.entity.User;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

	@Query("select * from User u where u.username = :username")
	Optional<User> findByUsername(@Param("username") String username);
}
