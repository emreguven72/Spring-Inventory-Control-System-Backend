package com.emregvn.inventorysystem.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emregvn.inventorysystem.entity.Category;
import com.emregvn.inventorysystem.model.responses.GetCategoryResponse;
import com.emregvn.inventorysystem.repository.CategoryRepository;
import com.emregvn.inventorysystem.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
	private final CategoryRepository categoryRepository;

	@Override
	public List<GetCategoryResponse> findAll() {
		List<Category> rawCategories = categoryRepository.findAll();
		
		List<GetCategoryResponse> categoriesResponse = rawCategories.stream()
				.map(rawCategory -> GetCategoryResponse.builder()
						.id(rawCategory.getId())
						.name(rawCategory.getName())
						.build()).toList();
		
		return categoriesResponse;
	}

	@Override
	public GetCategoryResponse findByName(String name) {
		Category rawCategory = categoryRepository.findByName(name).orElseThrow();
		
		return GetCategoryResponse.builder()
				.id(rawCategory.getId())
				.name(rawCategory.getName())
				.build();
	}

}
