package com.emregvn.inventorysystem.service;

import java.util.List;

import com.emregvn.inventorysystem.model.responses.GetInventoryResponse;

public interface InventoryService {

	List<GetInventoryResponse> findAllByUser(int id);
	
}
