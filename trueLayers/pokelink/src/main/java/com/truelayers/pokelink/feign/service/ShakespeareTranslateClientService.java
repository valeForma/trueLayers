package com.truelayers.pokelink.feign.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truelayers.pokelink.feign.client.ShakespeareTranslateFeignClient;
import com.truelayers.shakespeare.model.ShakespeareTranslation;


@Service
public class ShakespeareTranslateClientService extends AbstractClientService {

	@Autowired
	ShakespeareTranslateFeignClient shakespeareTranslateFeignClient;
	
	public ShakespeareTranslation traslateToShakespeare(String content) throws Exception {
		
		return extractResponse(shakespeareTranslateFeignClient.traslateToShakespeare(content));
		
	}
}
