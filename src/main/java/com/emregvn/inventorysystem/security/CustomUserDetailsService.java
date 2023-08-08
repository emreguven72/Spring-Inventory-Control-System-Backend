package com.emregvn.inventorysystem.security;

import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.emregvn.inventorysystem.entity.User;
import com.emregvn.inventorysystem.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
	private final UserService userService;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userService.findByEmail(username).orElseThrow();
		
		return UserPrincipal.builder()
				.id(user.getId())
				.email(user.getEmail())
				.password(user.getPassword())
				.authorities(List.of(new SimpleGrantedAuthority("USER")))
				.build();
	}

}
