package com.emregvn.inventorysystem.service;

import com.emregvn.inventorysystem.model.requests.LoginRequest;
import com.emregvn.inventorysystem.model.requests.RegisterRequest;
import com.emregvn.inventorysystem.model.responses.GetUserResponse;
import com.emregvn.inventorysystem.model.responses.LoginResponse;
import com.emregvn.inventorysystem.model.responses.RegisterResponse;

public interface AuthService {
	
	LoginResponse login(LoginRequest loginRequest);
	
	RegisterResponse register(RegisterRequest registerRequest);
	
	GetUserResponse findByToken(String token);
}
