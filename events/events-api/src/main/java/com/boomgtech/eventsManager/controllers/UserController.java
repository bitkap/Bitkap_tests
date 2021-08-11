package com.boomgtech.eventsManager.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.boomgtech.eventsManager.entities.User;
import com.boomgtech.eventsManager.services.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	UserService userService;
	
	@GetMapping
	public ResponseEntity<List<User>> getAllUser() {
		
		List<User> listUsers = userService.getAllUser();
		return new ResponseEntity<List<User>>(listUsers, HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {

		User userSaved = userService.saveUser(user);
		return new ResponseEntity<User>(userSaved, HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<User> updateUser(@RequestBody User user) {
		
		User userToSave = userService.getOneById(user.getId()).get();
		userToSave.setName(user.getName());
		userToSave.setPhone(user.getPhone());
		userToSave.setEmail(user.getEmail());
		
		User userSaved = userService.saveUser(userToSave);
		return new ResponseEntity<User>(userSaved, HttpStatus.OK);
	}
	
	@DeleteMapping("{id}")
	public ResponseEntity<String> deleteUser(@PathVariable int id) {
		
		User user = userService.getOneById(id).get();
		userService.deleteUser(user);
		return new ResponseEntity<String>("Utilisateur supprimé avec succès!", HttpStatus.OK);
	}
}
