package com.emregvn.inventorysystem.model.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GetUserResponse {
	
	private int id;
	private String email;
	
}
