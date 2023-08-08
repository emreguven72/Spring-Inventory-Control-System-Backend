package com.emregvn.inventorysystem.model.requests;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class RegisterRequest {

	private String email;
	private String password;
	
}
