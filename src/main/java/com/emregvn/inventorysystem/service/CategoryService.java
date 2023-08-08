package com.emregvn.inventorysystem.service;

import java.util.List;

import com.emregvn.inventorysystem.model.responses.GetCategoryResponse;

public interface CategoryService {

	List<GetCategoryResponse> findAll();
	
	GetCategoryResponse findByName(String name);
	
}
