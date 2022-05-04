package com.truelayers.pokelink.exception.handler;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ErrorMessage {

	private HttpStatus statusCode;
	private String errorMessage;
}
