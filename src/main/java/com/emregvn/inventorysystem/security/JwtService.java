package com.emregvn.inventorysystem.security;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;

import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.emregvn.inventorysystem.entity.Token;
import com.emregvn.inventorysystem.service.TokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class JwtService {
	private final JwtProperties jwtProperties;
	private final TokenService tokenService;
	
	public String create(int id, String email, List<String> roles) {
		return JWT.create()
			.withSubject(String.valueOf(id))
			.withExpiresAt(Instant.now().plus(Duration.of(1, ChronoUnit.DAYS)))
			.withClaim("email", email)
			.withClaim("authorities", roles)
			.sign(Algorithm.HMAC256(jwtProperties.getSECRET_KEY()));
	}
	
	public DecodedJWT decode(String jwt) {
		return JWT.require(Algorithm.HMAC256(jwtProperties.getSECRET_KEY()))
			.build()
			.verify(jwt);
	}
	
	public String checkIfTokenValid(String jwt) {
		
		Token token = tokenService.findByToken(jwt).orElseThrow();
		
		boolean isTokenValid = (!token.isExpired() && !token.isRevoked());
		
		if(!isTokenValid) {
			throw new RuntimeException("Access Token is not valid");
		}
		
		return jwt;
	}
	
}
