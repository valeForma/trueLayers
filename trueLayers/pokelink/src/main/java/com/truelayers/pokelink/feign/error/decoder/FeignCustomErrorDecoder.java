package com.truelayers.pokelink.feign.error.decoder;

import java.util.Date;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import com.truelayers.pokelink.exception.CustomInternalException;

import feign.Response;
import feign.RetryableException;
import feign.codec.ErrorDecoder;

public class FeignCustomErrorDecoder  implements ErrorDecoder {

	@Override
	public Exception decode(String methodKey, Response response) {

		switch ((int)(response.status()/100)) {
		case 5:
			return new RetryableException(response.status(),
					"Service Unvailable",
					response.request().httpMethod(),
					new Date(),
					null);
		case 4:
			return new CustomInternalException(Optional.ofNullable(response.body().toString()).orElse("unable to find the resource "),HttpStatus.resolve(response.status()));
		case 3:
			return new CustomInternalException(Optional.ofNullable(response.body().toString()).orElse("Resource moved "),HttpStatus.resolve(response.status()));
		
		default:
			return new CustomInternalException("Uknown errror",HttpStatus.resolve(response.status()));
		}
	}
	
}
