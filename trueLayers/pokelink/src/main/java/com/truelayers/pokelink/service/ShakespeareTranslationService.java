package com.truelayers.pokelink.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truelayers.pokelink.exception.CustomInternalException;
import com.truelayers.pokelink.feign.service.ShakespeareTranslateClientService;
import com.truelayers.shakespeare.model.ShakespeareTranslation;

@Service
public class ShakespeareTranslationService {

	@Autowired
	ShakespeareTranslateClientService shakespeareTranslateClientService;
	
	
	public ShakespeareTranslation translateContent(String content) throws Exception{
		content=Optional.ofNullable(content)
				 .map(x->x.replaceAll("[\\t\\n\\r]+"," "))
				 	.orElseThrow(()->new CustomInternalException("invalid content recieved"));
		ShakespeareTranslation translation=shakespeareTranslateClientService.traslateToShakespeare(content);
		return Optional.ofNullable(translation)
					.map(x->x)
						.orElseThrow(()->new CustomInternalException("unable to traslate the content"));
	}
}
