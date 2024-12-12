package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.Users;
import com.edigest.journalApp.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {


	private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	@Autowired
	private UserRepo userRepo;

	@Transactional
	public Users createUser( Users users ) {
		users.setPassword(passwordEncoder.encode(users.getPassword()));
		users.setRole(Arrays.asList("USER"));
		return userRepo.save(users);
	}


	public Users createNewUser( Users users ) {
		return userRepo.save(users);
	}

	//	Retrieve all data
	public ResponseEntity<List<Users>> getAll() {
		List<Users> all = userRepo.findAll();
		if( all != null && all.isEmpty() ) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);

		}
		return new ResponseEntity<>(all, HttpStatus.OK);
	}

	public Users getDataById( ObjectId id ) {
		Users dataById = userRepo.findById(id).orElse(null);
		return dataById;
	}

	public Users getDataByName( String name ) {
		Users dataById = userRepo.findByUserName(name);
		return dataById;
	}


	public ResponseEntity<Users> updateByName( String name, Users newEntry ) {
		Users old = userRepo.findByUserName(name);

		if( old != null ) {
			old.setUserName(newEntry.getUserName() != null &&
					                !newEntry.getUserName().equals("") ? newEntry.getUserName() : old.getUserName());
			old.setPassword(newEntry.getPassword() != null &&
					                !newEntry.getPassword().equals("") ? newEntry.getPassword() : old.getPassword());
			userRepo.save(old);
			return new ResponseEntity<>(old, HttpStatus.ACCEPTED);
		}
		return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
	}

//	Delete By Id

	public ResponseEntity<?> deleteDataById( ObjectId id ) {
		userRepo.deleteById(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}