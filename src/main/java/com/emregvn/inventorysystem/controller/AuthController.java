package com.emregvn.inventorysystem.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emregvn.inventorysystem.model.requests.LoginRequest;
import com.emregvn.inventorysystem.model.requests.RegisterRequest;
import com.emregvn.inventorysystem.model.responses.GetUserResponse;
import com.emregvn.inventorysystem.model.responses.LoginResponse;
import com.emregvn.inventorysystem.model.responses.RegisterResponse;
import com.emregvn.inventorysystem.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
	private final AuthService authService;
	
	@PostMapping("/login")
	public LoginResponse login(@RequestBody @Validated LoginRequest loginRequest) {
		return authService.login(loginRequest);
	}
	
	@PostMapping("/register")
	public RegisterResponse register(@RequestBody @Validated RegisterRequest registerRequest) {
		return authService.register(registerRequest);
	}
	
	@GetMapping("/get-user-by-token")
	public GetUserResponse getUserByToken(@RequestParam String token) {
		return authService.findByToken(token);
	}

}
