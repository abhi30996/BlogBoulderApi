package com.blogboulder.BlogBoulderApi.services;

import com.blogboulder.BlogBoulderApi.models.dto.BlogDto;
import com.blogboulder.BlogBoulderApi.models.entities.Blog;
import com.blogboulder.BlogBoulderApi.models.entities.User;
import com.blogboulder.BlogBoulderApi.models.repositories.BlogRepository;
import com.blogboulder.BlogBoulderApi.models.repositories.UserRepository;
import com.blogboulder.BlogBoulderApi.utils.TextUtils;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BlogService {

	private final BlogRepository blogRepository;
	private final UserRepository userRepository;
	private final MapperFacade mapperFacade;

	@Autowired
	public BlogService(BlogRepository blogRepository, UserRepository userRepository, MapperFacade mapperFacade) {
		this.blogRepository = blogRepository;
		this.userRepository = userRepository;
		this.mapperFacade = mapperFacade;
	}

	//Create

	@Transactional
	public ResponseEntity<?> addBlog(BlogDto blogDto) {
		if (TextUtils.isNullOrEmpty(blogDto.getContent()) || TextUtils.isNullOrEmpty(blogDto.getUserId()))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		User user = userRepository.findById(blogDto.getUserId()).orElse(null);
		if (user == null)
			return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		Blog blog = blogDto.toModel(Blog.class, mapperFacade);
		blogRepository.save(blog);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//Update

	@Transactional
	public ResponseEntity<?> editBlog(BlogDto blogDto) {
		if (TextUtils.isNullOrEmpty(blogDto.getId()) || TextUtils.isNullOrEmpty(blogDto.getUserId()))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		User user = userRepository.findById(blogDto.getUserId()).orElse(null);
		if (user == null)
			return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		Blog blog = blogRepository.findById(blogDto.getId()).orElse(null);
		if (blog == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		if(!blog.getUserId().equals(user.getId()))
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
		blog.setContent(blogDto.getContent());
		blogRepository.save(blog);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	//Read

	public ResponseEntity<?> fetchAllBlogs() {
		List<Blog> blogs = blogRepository.findAllByAndDeletedDateIsNull();
		if (CollectionUtils.isEmpty(blogs))
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		return ResponseEntity.ok(blogs.stream().filter(Objects::nonNull).map(obj -> obj.toDTO(BlogDto.class, mapperFacade)).collect(Collectors.toList()));
	}

	// Delete

	public ResponseEntity<?> deleteBlog(BlogDto blogDto) {
		if (TextUtils.isNullOrEmpty(blogDto.getId()) || TextUtils.isNullOrEmpty(blogDto.getUserId()))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		Blog blog = blogRepository.findByIdAndDeletedDateIsNull(blogDto.getId());
		if (blog == null)
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		User user = userRepository.findById(blogDto.getUserId()).orElse(null);
		if (user == null)
			return new ResponseEntity<>(HttpStatus.NON_AUTHORITATIVE_INFORMATION);

		if (!blog.getUserId().equals(user.getId()))
			return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

		blog.setDeletedDate(new Date());
		blogRepository.save(blog);

		return new ResponseEntity<>(HttpStatus.OK);
	}
}
