package com.truelayers.pokelink.feign.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truelayers.pokelink.feign.client.PokemonFeignClient;
import com.truelayers.pokemon.model.Pagination;
import com.truelayers.pokemon.model.Pokemon;

@Service
public class PokemonClientService extends AbstractClientService{
	
	@Autowired
	PokemonFeignClient pokemonFeignClient;
	
	public Pagination getAvailablePokemon(Integer offset,
			Integer limit) throws Exception{
		return extractResponse(pokemonFeignClient.getAvailablePokemon(offset, limit));
	}
	
	public Pokemon getPokemonByname(String name) throws Exception{
		return extractResponse(pokemonFeignClient.getPokemonByname(name));
	}
	
}
