package com.emregvn.inventorysystem.model.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetInventoryResponse {
	
	private int id;
	private int quantity;
	private String itemName;
	
}
