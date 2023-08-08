package com.emregvn.inventorysystem.service;

import java.util.List;

import com.emregvn.inventorysystem.model.responses.GetItemResponse;

public interface ItemService {
	
	GetItemResponse getById(int id);
	
	GetItemResponse getByName(String name);
	
	List<GetItemResponse> findAllByCategory(int categoryId);
	
}
