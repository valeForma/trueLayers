package com.truelayers.pokelink.config;

import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.Retryer;

@Configuration
public class FeignConfig {

	
	@Bean
	public Retryer retryer() {
	    return new Retryer.Default(100L, TimeUnit.SECONDS.toMillis(3L), 5) ;
	}
	
}
