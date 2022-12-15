package com.vithal.code.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.vithal.code.entities.User;

public class CustomeUserDetail implements UserDetails {
	
	/**
	 * some steps to implement security from Databse
	 * 
	 * 1:create CustomuseDetails implement userdetails
	 * 2:create customeUserDetailService implemeent userdetailservice
	 * 3:crreate Securityconfig class create some beans and map the url's
	 */

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private User user;
	
	public CustomeUserDetail(User user) {
		
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(user.getRole());
		return List.of(simpleGrantedAuthority);
	}

	@Override
	public String getPassword() {
	
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		
		return user.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
	
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
	
		return true;
	}

	@Override
	public boolean isEnabled() {
		
		return true;
	}

}
