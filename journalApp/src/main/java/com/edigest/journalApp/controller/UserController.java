package com.edigest.journalApp.controller;


import com.edigest.journalApp.entity.Users;
import com.edigest.journalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
	@Autowired
	private UserService userService;

	@PostMapping
	public Users create(
			@RequestBody Users Users
	                                  ) {
		return userService.createUser(Users);

	}

	@GetMapping
	public ResponseEntity<List<Users>> allData() {
		return userService.getAll();
	}

	@GetMapping("/id/{id}")
	public Users getDataByid(
			@PathVariable ObjectId id
	                                                 ) {
		return userService.getDataById(id);
	}

	@DeleteMapping("/id/{id}")
	public ResponseEntity<?> deleteDataById(
			@PathVariable ObjectId id
	                                       ) {
		return userService.deleteDataById(id);
	}

	@PutMapping("/{name}")
	public ResponseEntity<Users> updateDataByName(
			@PathVariable String name,
			@RequestBody Users updatedUser
	                                            ) {
		return userService.updateByName(name, updatedUser);

	}
}
