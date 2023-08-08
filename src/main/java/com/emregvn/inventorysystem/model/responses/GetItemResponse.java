package com.emregvn.inventorysystem.model.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetItemResponse {
	
	private int id;
	private String name;
	private Double price;
	private String categoryName;
	
}
