package com.blogboulder.BlogBoulderApi.controllers;

import com.blogboulder.BlogBoulderApi.models.dto.BlogDto;
import com.blogboulder.BlogBoulderApi.services.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/api/blog")
public class BlogController {

	private final BlogService blogService;

	@Autowired
	public BlogController(BlogService blogService) {
		this.blogService = blogService;
	}

	@PostMapping("/upload-blog")
	public ResponseEntity<?> addBlog(@RequestBody BlogDto blogDto){
		return blogService.addBlog(blogDto);
	}

	@PutMapping("/edit-blog")
	public ResponseEntity<?> editBlog(@RequestBody BlogDto blogDto){
		return blogService.editBlog(blogDto);
	}

	@GetMapping("/all")
	public ResponseEntity<?> fetchAllBlogs(){ return blogService.fetchAllBlogs(); }

	@GetMapping("/{blogId}")
	public ResponseEntity<?> fetchBlog(@PathVariable("blogId") String blogId){ return blogService.fetchBlog(blogId); }

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteBlog(@RequestBody BlogDto blogDto){
		return blogService.deleteBlog(blogDto);
	}
}
