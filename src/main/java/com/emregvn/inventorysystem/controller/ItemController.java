package com.emregvn.inventorysystem.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emregvn.inventorysystem.model.responses.GetItemResponse;
import com.emregvn.inventorysystem.service.ItemService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/items")
@RequiredArgsConstructor
public class ItemController {
	private final ItemService itemService;
	
	@GetMapping("/get/{id}")
	public GetItemResponse getById(@PathVariable int id) {
		return itemService.getById(id);
	}
	
	@GetMapping("/get-by-name")
	public GetItemResponse getByName(@RequestParam @Validated String name) {
		return itemService.getByName(name);
	}
	
	@GetMapping("/get-all-by-category/{id}")
	public List<GetItemResponse> findAllByCategory(@PathVariable int id) {
		return itemService.findAllByCategory(id);
	}
	
}
