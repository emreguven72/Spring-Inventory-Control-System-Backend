package com.emregvn.inventorysystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emregvn.inventorysystem.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
	
	Optional<Category> findByName(String name);
	
}
