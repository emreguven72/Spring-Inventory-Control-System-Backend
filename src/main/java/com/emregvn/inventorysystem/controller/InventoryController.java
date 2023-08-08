package com.emregvn.inventorysystem.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emregvn.inventorysystem.model.responses.GetInventoryResponse;
import com.emregvn.inventorysystem.service.InventoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/inventories")
@RequiredArgsConstructor
public class InventoryController {
	private final InventoryService inventoryService;

	@GetMapping("/get-all-by-user/{id}")
	public List<GetInventoryResponse> findAllByUser(@PathVariable @Validated int id) {
		return inventoryService.findAllByUser(id);
	}
	
}
