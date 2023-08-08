package com.emregvn.inventorysystem.service.Impl;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.emregvn.inventorysystem.entity.Token;
import com.emregvn.inventorysystem.entity.TokenType;
import com.emregvn.inventorysystem.entity.User;
import com.emregvn.inventorysystem.model.requests.LoginRequest;
import com.emregvn.inventorysystem.model.requests.RegisterRequest;
import com.emregvn.inventorysystem.model.responses.GetUserResponse;
import com.emregvn.inventorysystem.model.responses.LoginResponse;
import com.emregvn.inventorysystem.model.responses.RegisterResponse;
import com.emregvn.inventorysystem.security.JwtService;
import com.emregvn.inventorysystem.security.UserPrincipal;
import com.emregvn.inventorysystem.service.AuthService;
import com.emregvn.inventorysystem.service.TokenService;
import com.emregvn.inventorysystem.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{
	private final UserService userService;
	private final JwtService jwtService;
	private final TokenService tokenService;
	private final AuthenticationManager authenticationManager;

	@Override
	public LoginResponse login(LoginRequest loginRequest) {
		//check if user exists with those credentials
		//if exists set SecurityContextHolder
		//revoke all previous tokens
		//create new token for this user
		//return LoginResponse
		
		var authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword())
		);
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		
		var roles = userPrincipal.getAuthorities().stream()
						.map(GrantedAuthority::getAuthority)
						.toList();
		
		String jwt = jwtService.create(userPrincipal.getId(), userPrincipal.getEmail(), roles);
		
		User authUser = userService.findById(userPrincipal.getId()).orElseThrow();
		
		revokeAllUserTokens(authUser);
		
		createTokenForUser(jwt, authUser);
		
		return LoginResponse.builder()
				.message("You have logged in successfully as " + authUser.getEmail())
				.accessToken(jwt)
				.build();
	}

	@Override
	public RegisterResponse register(RegisterRequest registerRequest) {
		//create new user
		//create new jwt
		//create new token for this user
		
		User user = User.builder()
				.email(registerRequest.getEmail())
				.password(registerRequest.getPassword())
				.build();
		
		userService.save(user);
		
		String jwt = jwtService.create(user.getId(), user.getEmail(), List.of("USER"));
		
		createTokenForUser(jwt, user);
		
		return RegisterResponse.builder()
				.message("You have registered successfully as " + user.getEmail())
				.accessToken(jwt)
				.build();
	}
	
	private void createTokenForUser(String jwt, User user) {
		Token token = Token.builder()
				.token(jwt)
				.type(TokenType.BEARER)
				.expired(false)
				.revoked(false)
				.user(user)
				.build();
		
		tokenService.save(token);
	}
	
	private void revokeAllUserTokens(User user) {
		List<Token> validTokens = tokenService.findAllValidTokensByUser(user.getId());
		
		if(validTokens.isEmpty()) {
			return;
		}
		
		validTokens.forEach(token -> {
			token.setExpired(true);
			token.setRevoked(true);
			tokenService.save(token);
		});
	}

	@Override
	public GetUserResponse findByToken(String token) {
		DecodedJWT decodedToken = jwtService.decode(token);
		int userId = Integer.valueOf(decodedToken.getSubject());
		User user = userService.findById(userId).orElseThrow();
		
		return GetUserResponse.builder()
				.id(user.getId())
				.email(user.getEmail())
				.build();
	}
	
	
	
}
