package com.emregvn.inventorysystem.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emregvn.inventorysystem.entity.Inventory;
import com.emregvn.inventorysystem.model.responses.GetInventoryResponse;
import com.emregvn.inventorysystem.repository.InventoryRepository;
import com.emregvn.inventorysystem.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
	private final InventoryRepository inventoryRepository;

	@Override
	public List<GetInventoryResponse> findAllByUser(int id) {
		List<Inventory> rawInventories = inventoryRepository.findAllByUser(id);
		
		List<GetInventoryResponse> inventoriesResponse = rawInventories.stream()
				.map(rawInventory -> GetInventoryResponse.builder()
						.id(rawInventory.getId())
						.quantity(rawInventory.getQuantity())
						.itemName(rawInventory.getItem().getName())
						.build()).toList();
		
		return inventoriesResponse;
	}

}
