package com.emregvn.inventorysystem.service.Impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emregvn.inventorysystem.entity.Item;
import com.emregvn.inventorysystem.model.responses.GetItemResponse;
import com.emregvn.inventorysystem.repository.ItemRepository;
import com.emregvn.inventorysystem.service.ItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {
	private final ItemRepository itemRepository;

	@Override
	public GetItemResponse getById(int id) {
		Item item = itemRepository.findById(id).orElseThrow();
		
		return GetItemResponse.builder()
				.id(item.getId())
				.name(item.getName())
				.price(item.getPrice())
				.categoryName(item.getCategory().getName())
				.build();
	}

	@Override
	public GetItemResponse getByName(String name) {
		Item item = itemRepository.findByName(name).orElseThrow();
		
		return GetItemResponse.builder()
				.id(item.getId())
				.name(item.getName())
				.price(item.getPrice())
				.categoryName(item.getCategory().getName())
				.build();
	}

	@Override
	public List<GetItemResponse> findAllByCategory(int categoryId) {
		List<Item> rawItems = itemRepository.findAllByCategory(categoryId);
		
		List<GetItemResponse> itemsResponse = rawItems.stream()
				.map(rawItem -> GetItemResponse.builder()
						.id(rawItem.getId())
						.name(rawItem.getName())
						.price(rawItem.getPrice())
						.categoryName(rawItem.getCategory().getName())
						.build()).toList();
				
		return itemsResponse;
	}

}
