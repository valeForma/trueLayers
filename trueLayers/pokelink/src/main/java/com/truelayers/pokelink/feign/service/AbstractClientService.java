package com.truelayers.pokelink.feign.service;

import org.springframework.http.ResponseEntity;

public class AbstractClientService {

	
	protected <T extends Object> T extractResponse(ResponseEntity<T> response) {
		return response.getStatusCodeValue()>199 && response.getStatusCodeValue()<300 ? response.getBody() : null;
	}
}
