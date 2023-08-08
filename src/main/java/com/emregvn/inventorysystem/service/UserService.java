package com.emregvn.inventorysystem.service;

import java.util.List;
import java.util.Optional;

import com.emregvn.inventorysystem.entity.User;
import com.emregvn.inventorysystem.model.responses.GetUserResponse;

public interface UserService {
	
	List<User> findAll();
	
	Optional<User> findById(int id);
	
	Optional<User> findByEmail(String email);
	
	void save(User user);
	
}
