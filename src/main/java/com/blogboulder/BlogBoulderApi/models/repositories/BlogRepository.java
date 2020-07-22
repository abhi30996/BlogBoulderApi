package com.blogboulder.BlogBoulderApi.models.repositories;

import com.blogboulder.BlogBoulderApi.models.entities.Blog;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

@SuppressWarnings("SpringDataMethodInconsistencyInspection")
public interface BlogRepository extends MongoRepository<Blog, String> {
	List<Blog> findAllByAndDeletedDateIsNull();

	Blog findByIdAndDeletedDateIsNull(String id);
}
