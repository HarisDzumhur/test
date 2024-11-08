package com.example.app.client;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImp implements UserDetailsService{

	private final RegisteredClientRepository rp;
	
	
	
	public UserDetailsServiceImp(RegisteredClientRepository rp) {
		this.rp = rp;
	}



	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return rp.findBymail(username).orElseThrow(()->new UsernameNotFoundException("User with that mail doesn't exist!"));
	}

}
