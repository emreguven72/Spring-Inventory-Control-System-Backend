package com.emregvn.inventorysystem.service.Impl;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.emregvn.inventorysystem.entity.User;
import com.emregvn.inventorysystem.model.responses.GetUserResponse;
import com.emregvn.inventorysystem.repository.UserRepository;
import com.emregvn.inventorysystem.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
	private final UserRepository userRepository;
	
	@Override
	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> findById(int id) {
		return Optional.of(userRepository.findById(id).orElseThrow());
	}

	@Override
	public Optional<User> findByEmail(String email) {
		return Optional.of(userRepository.findByEmail(email).orElseThrow());
	}

	@Override
	public void save(User user) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}

}
