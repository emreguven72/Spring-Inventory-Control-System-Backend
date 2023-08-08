package com.emregvn.inventorysystem.service;

import java.util.List;
import java.util.Optional;

import com.emregvn.inventorysystem.entity.Token;

public interface TokenService {

	Optional<Token> findById(int id);
	
	Optional<Token> findByToken(String token);
	
	List<Token> findAllValidTokensByUser(int userId);
	
	void save(Token token);
	
}
