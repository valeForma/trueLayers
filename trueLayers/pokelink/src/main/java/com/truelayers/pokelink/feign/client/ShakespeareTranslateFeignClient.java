package com.truelayers.pokelink.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.truelayers.shakespeare.model.ShakespeareTranslation;

@FeignClient(name = "shakespeareFeignClient",url= "${feign.shakespeare.url}")
public interface ShakespeareTranslateFeignClient {

	
	@RequestMapping(path = "/translate/shakespeare.json" ,method = RequestMethod.GET,produces = "application/json")
	ResponseEntity<ShakespeareTranslation> traslateToShakespeare(@RequestParam("text")String text) throws Exception;
	
}
