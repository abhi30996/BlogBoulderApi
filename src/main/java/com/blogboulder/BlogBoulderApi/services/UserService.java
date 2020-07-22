package com.blogboulder.BlogBoulderApi.services;

import com.blogboulder.BlogBoulderApi.models.dto.UserDto;
import com.blogboulder.BlogBoulderApi.models.entities.User;
import com.blogboulder.BlogBoulderApi.models.repositories.UserRepository;
import com.blogboulder.BlogBoulderApi.utils.TextUtils;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

	private final UserRepository userRepository;
	private final MapperFacade mapperFacade;

	@Autowired
	public UserService(UserRepository userRepository, MapperFacade mapperFacade) {
		this.userRepository = userRepository;
		this.mapperFacade = mapperFacade;
	}

	@Transactional
	public ResponseEntity<?> registerUser(UserDto userDto) {
		if (TextUtils.isNullOrEmpty(userDto.getEmail()) || TextUtils.isNullOrEmpty(userDto.getPassword()) || TextUtils.isNullOrEmpty(userDto.getDisplayName()))
			return new ResponseEntity<>("Missing parameters", HttpStatus.BAD_REQUEST);
		if (userRepository.existsByEmail(userDto.getEmail()))
			return new ResponseEntity<>("Email address is already registered with the system!", HttpStatus.ALREADY_REPORTED);
		if (!TextUtils.isNullOrEmpty(userDto.getMobile()))
			if (userRepository.existsByMobile(userDto.getMobile()))
				return new ResponseEntity<>("Mobile Number is already registered with the system!", HttpStatus.ALREADY_REPORTED);
		if (userRepository.existsByDisplayNameAndDeletedDateIsNull(userDto.getDisplayName()))
			return new ResponseEntity<>("Please choose a unique display name.", HttpStatus.NOT_ACCEPTABLE);
		User user = userDto.toModel(User.class, mapperFacade);
		user.setVerified(true); //todo: Temporary. Implement proper Verification flow.
		userRepository.save(user);
		new Thread(() -> MailService.send(userDto.getEmail(), "Verify Email", "Thank you for signing up to Blog-Boulder!")).start();
		return new ResponseEntity<>(HttpStatus.OK);
	}

	public ResponseEntity<?> login(UserDto userDto) {
		if (TextUtils.isNullOrEmpty(userDto.getEmail()) || TextUtils.isNullOrEmpty(userDto.getPassword()))
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		User user = userRepository.findByEmailAndDeletedDateIsNull(userDto.getEmail());
		if (user == null || !user.getPassword().equals(userDto.getPassword()))
			return new ResponseEntity<>("Incorrect email or password", HttpStatus.NON_AUTHORITATIVE_INFORMATION);
		UserDto userDto1 = user.toDTO(UserDto.class, mapperFacade);
		return ResponseEntity.ok(userDto1);
	}
}
