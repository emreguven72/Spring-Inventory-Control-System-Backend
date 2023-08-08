package com.emregvn.inventorysystem.security;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import com.auth0.jwt.interfaces.DecodedJWT;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtUserPrincipalConverter {

	public UserPrincipal convert(DecodedJWT jwt) {
		return UserPrincipal.builder()
				.id(Integer.valueOf(jwt.getSubject()))
				.email(String.valueOf(jwt.getClaim("username")))
				.authorities(jwt.getClaim("authorities").asList(SimpleGrantedAuthority.class))
				.build();
	}
	
}
