package com.emregvn.inventorysystem.controller;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.emregvn.inventorysystem.model.responses.GetCategoryResponse;
import com.emregvn.inventorysystem.service.CategoryService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {
	private final CategoryService categoryService;
	
	@GetMapping("/get-all")
	public List<GetCategoryResponse> findAll() {
		return categoryService.findAll();
	}
	
	@GetMapping("get-by-name")
	public GetCategoryResponse findByName(@RequestParam @Validated String name) {
		return categoryService.findByName(name);
	}
	
}
