package com.example.tasks.rest;

import com.example.tasks.persistence.model.User;
import com.example.tasks.persistence.repository.UserRepository;
import com.example.tasks.rest.converter.UserConverter;
import com.example.tasks.rest.helper.UserHelper;
import com.example.tasks.rest.model.UserRequestModel;
import com.example.tasks.rest.model.UserResponseModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class UserController {

	private final UserRepository userRepository;

	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@PostMapping(value = "users")
	public ResponseEntity<UserResponseModel> create(@RequestBody UserRequestModel createRequest) {
		User user = new User(createRequest.getName(), createRequest.getSurname());
		User savedUser = userRepository.save(user);
		return ResponseEntity.ok(UserConverter.convertToResponseModel(savedUser));
	}

	@PutMapping(value = "users/{id}")
	public ResponseEntity<UserResponseModel> update(@PathVariable(value = "id") Long id, @RequestBody UserRequestModel updateRequest) {
		User user = userRepository.findById(id).get();
		UserHelper.updateUser(updateRequest, user);
		User savedUser = userRepository.save(user);
		return ResponseEntity.ok(UserConverter.convertToResponseModel(savedUser));
	}

	@GetMapping(value = "users/{id}")
	public ResponseEntity<UserResponseModel> getById(@PathVariable(value = "id") Long id) {
		User user = userRepository.findById(id).get();
		return ResponseEntity.ok(UserConverter.convertToResponseModel(user));
	}

	@GetMapping(value = "users")
	public ResponseEntity<List<UserResponseModel>> getAllUsers() {
		return ResponseEntity.ok(userRepository.findAll().stream().map(UserConverter::convertToResponseModel).collect(Collectors.toList()));
	}

	@DeleteMapping(value = "users/{id}")
	public ResponseEntity<Boolean> deleteById(@PathVariable(value = "id") Long id) {
		userRepository.deleteById(id);
		return ResponseEntity.ok(!userRepository.existsById(id));
	}
}
