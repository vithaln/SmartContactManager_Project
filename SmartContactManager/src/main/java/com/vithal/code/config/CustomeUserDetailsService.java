package com.vithal.code.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vithal.code.entities.User;
import com.vithal.code.repository.UserRepo;

public class CustomeUserDetailsService implements UserDetailsService  {

	@Autowired
	private UserRepo repo;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	//fetch user from database
		
		User user = repo.getUserByUserName(username);
		
		if(user==null) {
			throw new UsernameNotFoundException("could not found user!");
		}
		
		CustomeUserDetail customeUserDetail = new CustomeUserDetail(user);
		
		return customeUserDetail;
	}

}
