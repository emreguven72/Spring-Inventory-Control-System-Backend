package com.emregvn.inventorysystem.model.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetCategoryResponse {
	
	private int id;
	private String name;
	
}
