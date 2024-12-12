package com.edigest.journalApp.service;

import com.edigest.journalApp.entity.Users;
import com.edigest.journalApp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailServiceImpl implements UserDetailsService {


	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername( String username ) throws UsernameNotFoundException {
		Users users = userRepo.findByUserName(username);
		if( users != null ) {
			return User.builder()
			           .username(users.getUserName())
			           .password(users.getPassword())
			           .roles(users.getRole().toArray(new String[0]))
			           .build();
		}
		throw new UsernameNotFoundException("No user found with that identifier" + username);
	}
}
