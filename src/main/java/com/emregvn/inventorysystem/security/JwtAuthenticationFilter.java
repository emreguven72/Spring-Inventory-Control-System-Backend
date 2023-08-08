package com.emregvn.inventorysystem.security;

import java.io.IOException;
import java.util.Optional;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
	private final JwtService jwtService;
	private final JwtUserPrincipalConverter jwtUserPrincipalConverter;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
		//get token from request
		//decode the token
		//create UserPrincipal from token information
		//create UserPrincipalAuthenticationToken from UserPrincipal
		//set SecurityContextHolder with UserPrincipalAuthenticationToken
		
		extractTokenFromHeader(request)
			.map(jwtService::checkIfTokenValid)
			.map(jwtService::decode)
			.map(jwtUserPrincipalConverter::convert)
			.map(UserPrincipalAuthenticationToken::new)
			.ifPresent(authentication -> SecurityContextHolder.getContext().setAuthentication(authentication));
		
		filterChain.doFilter(request, response);
	}
	
	private Optional<String> extractTokenFromHeader(HttpServletRequest request) {
		var header = request.getHeader("Authorization");
		
		if(!StringUtils.hasText(header) || !header.startsWith("Bearer ")) {
			return Optional.empty();
		}
		
		return Optional.of(header.substring(7));
	}
	
}
