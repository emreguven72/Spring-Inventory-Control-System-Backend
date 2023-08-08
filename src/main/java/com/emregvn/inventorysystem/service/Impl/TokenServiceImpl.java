package com.emregvn.inventorysystem.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emregvn.inventorysystem.entity.Token;
import com.emregvn.inventorysystem.repository.TokenRepository;
import com.emregvn.inventorysystem.service.TokenService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService{
	private final TokenRepository tokenRepository;
	
	@Override
	public Optional<Token> findById(int id) {
		return Optional.of(tokenRepository.findById(id).orElseThrow());
	}

	@Override
	public Optional<Token> findByToken(String token) {
		return Optional.of(tokenRepository.findByToken(token).orElseThrow());
	}

	@Override
	public List<Token> findAllValidTokensByUser(int userId) {
		return tokenRepository.findAllValidTokensByUser(userId);
	}

	@Override
	public void save(Token token) {
		tokenRepository.save(token);
	}

}
