package com.truelayers.pokelink.exception;

import org.springframework.http.HttpStatus;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CustomInternalException extends RuntimeException{

	private String customErrorMessage;
	private HttpStatus httpStatusCode=HttpStatus.INTERNAL_SERVER_ERROR;
	
	public CustomInternalException(String customErrorMessage){
		super(customErrorMessage);
		this.customErrorMessage=customErrorMessage;
		
	}
	
	public CustomInternalException(String customErrorMessage,HttpStatus httpStatusCode){
		super(customErrorMessage);
		this.customErrorMessage=customErrorMessage;
		this.httpStatusCode=httpStatusCode;
		
	}
	public CustomInternalException(String customErrorMessage,HttpStatus httpStatusCode,Throwable exception){
		super(customErrorMessage,exception);
		this.customErrorMessage=customErrorMessage;
		this.httpStatusCode=httpStatusCode;
		
		
	}

	
}
