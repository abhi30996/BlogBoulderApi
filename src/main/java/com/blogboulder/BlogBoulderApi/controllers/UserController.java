package com.blogboulder.BlogBoulderApi.controllers;

import com.blogboulder.BlogBoulderApi.models.dto.UserDto;
import com.blogboulder.BlogBoulderApi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDto userDto){
		return userService.registerUser(userDto);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDto userDto){
		return userService.login(userDto);
	}
}
