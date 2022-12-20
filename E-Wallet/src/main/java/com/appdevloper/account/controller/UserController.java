package com.appdevloper.account.controller;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.appdevloper.account.exception.ResourceNotFoundException;
import com.appdevloper.account.model.LoginModel;
import com.appdevloper.account.model.UsersModel;
import com.appdevloper.account.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private BCryptPasswordEncoder encoder;

	@GetMapping("/{id}")
	public ResponseEntity<UsersModel> getUserModel(@PathVariable Integer id) {
		try {
			UsersModel usersModel = userService.loadUserId(id);
			return ResponseEntity.ok(usersModel);
		} catch (Exception e) {
			throw new ResourceNotFoundException("user not found with the following data : "+id);
		}
	}
  


	@GetMapping("user/{username}")
	public ResponseEntity<UsersModel> getUser(@PathVariable String username) {
		try {
			UsersModel usersModel = userService.loadUserByUsername(username);
			return ResponseEntity.ok(usersModel);
		} catch (Exception e) {
			throw new ResourceNotFoundException("user not found with the following data : "+username);
		}
	}

	@PostMapping("/user")
	public ResponseEntity<UsersModel> saveUserData(@Valid @RequestBody UsersModel usersModel) {
		UsersModel usersModel2 = new UsersModel();
		usersModel2.setId(usersModel.getId());
		usersModel2.setEmail(usersModel.getEmail());
		usersModel2.setUsername(usersModel.getUsername());
		usersModel2.setPassword(encoder.encode(usersModel.getPassword()));
		
		UsersModel users = userService.addUser(usersModel2);
		if (users == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(users, HttpStatus.CREATED);
		}

	}

	@DeleteMapping("user/{id}")
	public ResponseEntity<Boolean> deleteUserModel(@PathVariable Integer id) {
		UsersModel usersModel = userService.loadUserId(id);

		if (usersModel != null) {
			userService.deleteUser(usersModel);
			return ResponseEntity.ok(true);
		} else
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

	}

	@PutMapping("user/{id}")
	public ResponseEntity<UsersModel> updateUserModel(@Valid @PathVariable Integer id,
			@RequestBody UsersModel usersModel) {
		try {
			UsersModel users = userService.loadUserId(id);

			if (users != null) {
				users.setEmail(users.getEmail());
				users.setId(users.getId());
				users.setPassword(encoder.encode(usersModel.getPassword()));
				users.setUsername(usersModel.getUsername());

				UsersModel updaUsersModel = userService.addUser(users);
				return ResponseEntity.ok(updaUsersModel);

			} else
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			throw new ResourceNotFoundException("user not found with the following data !");
		}

	}

	@PostMapping("user/login")
	public ResponseEntity<UsersModel> userLogin(@RequestBody LoginModel loginModel) {

		String username = loginModel.getUsername();
		String passcode = loginModel.getPassword();
		
		UsersModel usersModel = userService.loadUserByUsername(username);
		
		if (encoder.matches(passcode ,usersModel.getPassword())) {
			return ResponseEntity.ok(usersModel);}
		else {
			
			if (usersModel.getUsername() != null) {
				throw new ResourceNotFoundException("Invalid in password : " + passcode);
			}else {

				throw new ResourceNotFoundException("Invalid  Username : " + username);
			}
		}
	}

}
