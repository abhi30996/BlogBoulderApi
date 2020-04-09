package com.blogboulder.BlogBoulderApi.models.repositories;

import com.blogboulder.BlogBoulderApi.models.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
	boolean existsByEmail(String email);

	boolean existsByMobile(String mobile);

	User findByEmailAndDeletedDateIsNull(String email);

	boolean existsByDisplayNameAndDeletedDateIsNull(String displayName);
}
