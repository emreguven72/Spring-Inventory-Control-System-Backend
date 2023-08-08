package com.emregvn.inventorysystem.model.responses;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterResponse {

	private String message;
	private String accessToken;
	
}
