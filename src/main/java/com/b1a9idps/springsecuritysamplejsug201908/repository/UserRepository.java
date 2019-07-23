package com.b1a9idps.springsecuritysamplejsug201908.repository;

import com.b1a9idps.springsecuritysamplejsug201908.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
